package eu.cryptoapp.explorecountry.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.widget.doOnTextChanged
import com.google.android.material.bottomsheet.BottomSheetDialog
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


        val btnSwitch : ImageButton = findViewById(R.id.btn_switch)

        var isChecked = false

//        btnSwitch.setOnClickListener {
//            isChecked = !isChecked
//            if (isChecked) {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            } else {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//            }
//        }




        val btnFilter : Button = findViewById(R.id.filter_btn)

        btnFilter.setOnClickListener {
            val view : View = layoutInflater.inflate(R.layout.activity_filter, null)
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(view)
            dialog.show()

            view.findViewById<ImageButton>(R.id.btn_return).setOnClickListener {
                dialog.dismiss()
            }

            view.findViewById<ImageButton>(R.id.continent_dropdown).setOnClickListener {
                val view : View = layoutInflater.inflate(R.layout.activity_filter_selection, null)
                val dialog = BottomSheetDialog(this)
                dialog.setContentView(view)
                dialog.show()

                view.findViewById<ImageButton>(R.id.btn_exit2).setOnClickListener {
                    dialog.dismiss()
                }
            }

           view.findViewById<ImageButton>(R.id.timezone_dropdown2).setOnClickListener {
               val view : View = layoutInflater.inflate(R.layout.activity_filter_selection, null)
               val dialog = BottomSheetDialog(this)
               dialog.setContentView(view)
               dialog.show()
           }
        }


        val preferredLanguage : Button = findViewById(R.id.preferred_lang)

        preferredLanguage.setOnClickListener {
            val view : View = layoutInflater.inflate(R.layout.preferred_language_activity_filter, null)
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(view)
            dialog.show()

            view.findViewById<ImageButton>(R.id.btn_exit).setOnClickListener {
                dialog.dismiss()
            }


        }



        val searchCountry : EditText = findViewById(R.id.search_country)

        searchCountry.doOnTextChanged { text, start, before, count ->
            filter(text?.toString() ?: "")
        }
    }

    fun onRadioButtonClicked(view: View){
        if (view is RadioButton){
            val checked = view.isChecked
        }
    }

    fun onCheckboxClicked(view: View){
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked
        }
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
        val updatedList = fullList.filter { it.name.common.contains(query, true) }
        listView.countries.value = updatedList
    }
}