package eu.cryptoapp.explorecountry.data

import eu.cryptoapp.explorecountry.models.Country
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("all")
    fun getData(): Call<List<Country>>
}