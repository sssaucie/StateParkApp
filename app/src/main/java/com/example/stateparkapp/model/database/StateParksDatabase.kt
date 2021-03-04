package com.example.stateparkapp.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.stateparkapp.model.entity.StateParks
import com.example.stateparkapp.model.dao.StateParksDao
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [StateParks::class], version = 1, exportSchema = false)
abstract class StateParksDatabase : RoomDatabase() {
    abstract val stateParksDao: StateParksDao

    companion object {
        @Volatile
        private var INSTANCE: StateParksDatabase? = null

        @InternalCoroutinesApi
        fun getInstance(context: Context): StateParksDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StateParksDatabase::class.java,
                        "state_parks_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}