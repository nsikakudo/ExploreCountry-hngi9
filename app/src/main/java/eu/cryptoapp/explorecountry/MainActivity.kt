package eu.cryptoapp.explorecountry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://restcountries.com/v3.1/"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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

                if (response.isSuccessful){

                }

                val responseBody = response.body()!!

                val myStringBuilder = StringBuilder()
                for (name in responseBody){
                    myStringBuilder.append(name.name)
                    myStringBuilder.append("\n")
                }

                Log.d("XXXX", myStringBuilder.toString())
                var tvName : TextView = findViewById(R.id.tvName)
                tvName.text = myStringBuilder.toString()
            }

            override fun onFailure(call: Call<List<Country>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: "+t.message)
            }
        })
    }
}