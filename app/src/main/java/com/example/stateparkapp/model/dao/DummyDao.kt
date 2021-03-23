package com.example.stateparkapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.stateparkapp.model.entity.Dummy

@Dao
interface DummyDao {
    @Insert
    suspend fun insert(dummy: Dummy)
}