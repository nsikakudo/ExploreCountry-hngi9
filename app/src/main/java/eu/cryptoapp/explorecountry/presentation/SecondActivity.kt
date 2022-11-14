package eu.cryptoapp.explorecountry.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import coil.Coil
import coil.load
import com.bumptech.glide.Glide
import com.google.android.material.appbar.MaterialToolbar
import eu.cryptoapp.explorecountry.R
import eu.cryptoapp.explorecountry.models.CountryDetails

class SecondActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val details = intent.getParcelableExtra("countryDetails") as CountryDetails?

//        val ivImage : ImageView = findViewById(R.id.iv_image)
        val tvPopulation : TextView = findViewById(R.id.tv_population_value)
        val tvRegion : TextView = findViewById(R.id.tv_region_value)
        val tvCapital : TextView = findViewById(R.id.tv_capital_value)
        val tvOfficialLang : TextView = findViewById(R.id.tv_official_lang_value)
        val tvDemonyms : TextView = findViewById(R.id.tv_demonym_value)
        val tvUnMember : TextView = findViewById(R.id.tv_un_member_value)
        val tvArea : TextView = findViewById(R.id.tv_area_value)
        val tvIndependent : TextView = findViewById(R.id.tv_independent_value)
        val tvCurrency : TextView = findViewById(R.id.tv_currency_value)
        val tvTimeZone : TextView = findViewById(R.id.tv_time_zone_value)
        val tvDrivingSide : TextView = findViewById(R.id.tv_driving_value)
        val tvLocation : TextView = findViewById(R.id.tv_geographical_value)
        val toolbar : MaterialToolbar = findViewById(R.id.btn_back)
        val pR = findViewById<MyPictureRow>(R.id.pictureRow)


        tvPopulation.text = details?.population.toString()
        tvRegion.text = details?.region.toString()
        tvCapital.text = details?.capital.toString()
        tvOfficialLang.text = details?.languages.toString()
        tvDemonyms.text = details?.demonyms.toString()
        tvUnMember.text = details?.unMembers.toString()
        tvArea.text = details?.area.toString()
        tvIndependent.text = details?.independent.toString()
        tvCurrency.text = details?.currency.toString()
        tvTimeZone.text = details?.timezones.toString()
        tvDrivingSide.text = details?.car.toString()
        tvLocation.text = details?.latlng.toString()


        pR.imageUrls.value = listOf(
            details?.flags ?: "",
            details?.coatOfArms ?: "", //update to coat of arms
           //Update to the other thing
        )
//        Glide.with(this)
//            .load(details?.flags)
//            .into(ivImage)


        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        //TODO:
//        toolbar.title = details.name

    }
}