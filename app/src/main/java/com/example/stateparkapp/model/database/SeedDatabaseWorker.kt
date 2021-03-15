package com.example.stateparkapp.model.database

import android.content.Context
import android.util.JsonReader
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.stateparkapp.model.entity.StateParks
import com.example.stateparkapp.utilities.STATE_PARKS_DATA_FILENAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.coroutineScope

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(STATE_PARKS_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val stateParkName = object : TypeToken<List<StateParks>>() {}.type
                    val stateParksList: List<StateParks> = Gson().fromJson(jsonReader, stateParkName)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.stateParksDao().insertAll(stateParksList)

                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
    }
}
