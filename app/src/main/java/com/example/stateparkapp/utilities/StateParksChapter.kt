package com.example.stateparkapp.utilities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StateParksChapter(
    @Json(name = "name") val parkName: String,
    @Json(name = "location") val county: String,
    val country: String,
    val region: String,
    val website: String,
    val geo: LatLong
): Parcelable

@Parcelize
data class LatLong(
    @Json(name = "latitude")
    val long: Double,
    @Json(name = "longitude")
    val lat: Double
) : Parcelable

//@Parcelize
//data class GdgResponse(
//    @Json(name = "filters_") val filters: Filter,
//    @Json(name = "data") val chapters: List<StateParksChapter>
//): Parcelable
//
//@Parcelize
//data class Filter(
//    @Json(name = "region") val regions: List<String>
//): Parcelable
