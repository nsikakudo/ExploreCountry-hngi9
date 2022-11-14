package eu.cryptoapp.explorecountry.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryDetails(
    val population: Int,
    val region: String,
    val capital: String?,
    val languages: String,// Official language
    val demonyms: String,
    val unMembers: Boolean,
    val independent: Boolean,
    val area: Double,
    val currency: String? = null,
    val timezones: List<String>,
    val latlng: List<Double>, //Geographical location
    val car: String,
    val flag: String,
    val flags: String,
    val coatOfArms: String,
) : Parcelable{

    companion object{
        fun fromCountry(country: Country) : CountryDetails {
            return CountryDetails(
                population = country.population,
                region = country.region,
                capital = country.capital?.firstOrNull() ?: "",
                languages = country.languages.values.firstOrNull() ?: "",
                demonyms = country.demonyms.eng.m,
                unMembers = country.unMember,
                independent = country.independent,
                area = country.area,
                currency = country.currencies.values?.joinToString(",") { it.name } ?: "",
//                currency = country.currencies.MRU?.name?: "",
                timezones = country.timezones,
                latlng = country.latlng,
                car = country.car.side,
                flag = country.flag,
                flags = country.flags.png,
                coatOfArms = country.coatOfArms.png,
            )
        }
    }
}
