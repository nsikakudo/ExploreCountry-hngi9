package eu.cryptoapp.explorecountry.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MRU(
    val name: String,
    val symbol: String
): Parcelable