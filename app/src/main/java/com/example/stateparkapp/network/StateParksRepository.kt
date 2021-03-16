package com.example.stateparkapp.network

import com.example.stateparkapp.model.dao.StateParksDao
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 *
 * Collecting from the Flows in [StateParksDao] is main-safe. Room supports Coroutines and moves the
 * query execution off of the main thread.
 */

@Singleton
class StateParksRepository @Inject constructor(private val stateParksDao: StateParksDao) {

    suspend fun getAll() = stateParksDao.getAll()

}