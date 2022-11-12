package eu.cryptoapp.explorecountry.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Demonyms(
    val eng: Eng,
    val fra: Fra
):Parcelable