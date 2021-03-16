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
//@TypeConverters(Converters::class)
abstract class StateParksDatabase : RoomDatabase() {

    abstract fun stateParksDao(): StateParksDao

    companion object {

        /**
         * Singleton prevents multiple instances of database opening at the same time.
         */

        @Volatile
        private var instance: StateParksDatabase? = null

        fun getInstance(context: Context): StateParksDatabase {
            /**
             * If the instance is not null, then return it.
             * If it is, then create the database.
             */
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        /**
         * Create and pre-populate the database.
          */

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

/**
 * Optionally, you could create the database without referencing the SeedDatabaseWorker, if not
 * working with one, as follows:
 *
 * @Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
 *   public abstract class WordRoomDatabase : RoomDatabase() {
 *
 *   abstract fun wordDao(): WordDao
 *
 *   companion object {
 *   @Volatile
 *   private var INSTANCE: WordRoomDatabase? = null
 *
 *   fun getDatabase(context: Context): WordRoomDatabase {
 *       return INSTANCE ?: synchronized(this) {
 *           val instance = Room.databaseBuilder(
 *               context.applicationContext,
 *               WordRoomDatabase::class.java,
 *               "word_database"
 *           ).build()
 *       INSTANCE = instance
 *       instance
 *       }
 *     }
 *   }
 * }
 */