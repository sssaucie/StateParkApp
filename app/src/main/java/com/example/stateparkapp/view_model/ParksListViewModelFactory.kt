package com.example.stateparkapp.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stateparkapp.model.dao.StateParksDao

/**
 * This is pretty much boiler plate code for a ViewModel Factory.
 *
 * Provides the StateParksDao and context to the ViewModel.
 */

class ParksListViewModelFactory(
    private val dataSource: StateParksDao,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ParksListViewModel::class.java)) {
            return ParksListViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}