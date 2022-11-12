package eu.cryptoapp.explorecountry.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import eu.cryptoapp.explorecountry.R
import eu.cryptoapp.explorecountry.data.ApiInterface
import eu.cryptoapp.explorecountry.models.Country
import eu.cryptoapp.explorecountry.models.CountryDetails
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://restcountries.com/v3.1/"

class MainActivity : AppCompatActivity() {

    var fullList = listOf<Country>()
    lateinit var listView: MyComposeListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById<MyComposeListView>(R.id.listView)
        getMyData()
    }

    private fun getMyData() {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<Country>?> {
            override fun onResponse(
                call: Call<List<Country>?>,
                response: Response<List<Country>?>
            ) {

                if (response.isSuccessful) {
                    val countries = response.body() ?: emptyList()
                    fullList = countries
                    val groupByInitials =
                        countries.groupBy { it.name.common[0].toString() }.toSortedMap()


                    with(listView) {
                        this.countries.value = fullList
//                        countriesGroupedByInitials.value = groupByInitials
                        onClick = { country ->
                            val intent = Intent(this@MainActivity, SecondActivity::class.java)
                            intent.putExtra("countryDetails", CountryDetails.fromCountry(country))
                            startActivity(intent)
                        }
                    }


                }

            }

            override fun onFailure(call: Call<List<Country>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
            }
        })

    }

    private fun filter(query: String) {
        if (query.isBlank()){
            listView.countries.value = fullList
            return
        }
        val updatedList = fullList.filter { it.name.common.contains(query) }
        listView.countries.value = updatedList
    }
}