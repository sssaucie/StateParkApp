package com.example.stateparkapp.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "state_parks")
data class StateParks(
    @PrimaryKey(autoGenerate = true)
    var parksId: Long = 0L,

    @ColumnInfo(name = "name")
    var parkName: String,

    @ColumnInfo(name = "type")
    var activityType: String,

    @ColumnInfo(name = "rating")
    var rating: Int,

    @ColumnInfo(name = "location")
    var county: String,

    @ColumnInfo(name = "size")
    var acreage: String,

    @ColumnInfo(name = "elevation")
    var elevation: String,

    @ColumnInfo(name = "year_established")
    var yearEstablished: Int,

    @ColumnInfo(name = "yearly_visitors")
    var yearlyVisitors: Int,

    @ColumnInfo(name = "short_description")
    var parkDescription: String,

    @ColumnInfo(name = "latitude")
    var latitude: Long,

    @ColumnInfo(name = "longitude")
    var longitude: Long,
)
