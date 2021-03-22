package com.example.stateparkapp.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "state_parks")
data class StateParks(
    @PrimaryKey(autoGenerate = true)
    val parksId: Long = 0L,

    @ColumnInfo(name = "name")
    val parkName: String,

    @ColumnInfo(name = "type")
    val activityType: String,

    @ColumnInfo(name = "rating")
    val rating: Int,

    @ColumnInfo(name = "location")
    val county: String,

    @ColumnInfo(name = "size")
    val acreage: String,

    @ColumnInfo(name = "elevation")
    val elevation: String,

    @ColumnInfo(name = "year_established")
    val yearEstablished: Int,

    @ColumnInfo(name = "yearly_visitors")
    val yearlyVisitors: Int,

    @ColumnInfo(name = "short_description")
    val parkDescription: String,

    @ColumnInfo(name = "latitude")
    val latitude: Long,

    @ColumnInfo(name = "longitude")
    val longitude: Long,
): Serializable
