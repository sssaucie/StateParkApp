package com.example.stateparkapp.model.database

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.stateparkapp.model.dao.StateParksDao
import com.example.stateparkapp.model.entity.StateParks
import com.example.stateparkapp.utilities.DATABASE_NAME

/**
 * The Room database for this app
 */

@Database(entities = [StateParks::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class StateParksDatabase : RoomDatabase() {

    abstract fun stateParksDao(): StateParksDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: StateParksDatabase? = null

        fun getInstance(context: Context): StateParksDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database.
        private fun buildDatabase(context: Context): StateParksDatabase {
            return Room.databaseBuilder(context, StateParksDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }
    }
}