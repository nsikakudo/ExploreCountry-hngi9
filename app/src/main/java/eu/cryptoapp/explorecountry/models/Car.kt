package eu.cryptoapp.explorecountry.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Car(
    val side: String,
    val signs: List<String>
): Parcelable