package com.example.stateparkapp.network

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.stateparkapp.model.dao.StateParksDao
import com.example.stateparkapp.model.entity.StateParks
import kotlinx.coroutines.flow.Flow

/**
 * Repository module for handling data operations.
 * Declare the DAO as a private property in the constructor, pass in the DAO instead of the whole
 * database, because access to DAO only is necessary.
 */

class StateParksRepository(private val stateParksDao: StateParksDao) {

    /**
     * Room executes all queries on a separate thread.
     * Observed LiveData will notify the observer when the data has changed.
     */

    val allParks: LiveData<List<StateParks>> = stateParksDao.getAll()

    /**
     * By default, Room runs suspend queries off the main thread; therefore, we don't need to
     * implement anything else to ensure we're not doing long running database work
     * off the main thread.
     */

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(parks: StateParks) {
        stateParksDao.insert(parks)
    }
}