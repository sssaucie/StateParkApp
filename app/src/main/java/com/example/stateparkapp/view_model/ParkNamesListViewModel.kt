package com.example.stateparkapp.view_model

import androidx.lifecycle.*
import com.example.stateparkapp.model.entity.StateParks
import com.example.stateparkapp.network.StateParksRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ParkNamesListViewModel(private val repository: StateParksRepository) : ViewModel() {

    /**
     * Using LiveData and caching what allParks returns has several benefits:
     * - We can put an observer on the data (instead of polling for changes) and only update
     *   the UI when the data actually changes.
     * - Repository is completely separated from the UI through the ViewModel.
     */

    val allParks: LiveData<List<StateParks>> = repository.allParks.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(parks: StateParks) = viewModelScope.launch {
        repository.insert(parks)
    }
}