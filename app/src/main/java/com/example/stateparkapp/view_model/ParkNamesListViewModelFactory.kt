package com.example.stateparkapp.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stateparkapp.network.StateParksRepository
import java.lang.IllegalArgumentException

class ParksListViewModelFactory(private val repository: StateParksRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ParkNamesListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ParkNamesListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}