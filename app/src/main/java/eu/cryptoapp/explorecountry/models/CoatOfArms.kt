package eu.cryptoapp.explorecountry.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoatOfArms(
    val png: String,
    val svg: String
): Parcelable