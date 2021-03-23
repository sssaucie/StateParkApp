package com.example.stateparkapp.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "state_parks")
data class StateParks(
    @PrimaryKey(autoGenerate = true)
    val parksId: Long = 0L,

    val name: String,

    val type: String,

    val rating: String,

    val location: String,

    val size: String,

    val elevation: String,

    val year_established: Int,

    val yearly_visitors: String,

    val short_description: String,

    val latitude: Double,

    val longitude: Double
): Serializable
