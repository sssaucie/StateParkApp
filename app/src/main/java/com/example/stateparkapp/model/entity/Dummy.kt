package com.example.stateparkapp.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "dummy")
data class Dummy(val title: String): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}