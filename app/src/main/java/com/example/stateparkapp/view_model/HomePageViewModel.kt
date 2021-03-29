package com.example.stateparkapp.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stateparkapp.model.entity.StateParks

class HomePageViewModel {

    private val _navigateToStatePark = MutableLiveData<StateParks>()

    /**
     * If this is non-null, immediately navigate to [StateParkFragment] and call [doneNavigating]
     */
    val navigateToStatePark: LiveData<StateParks>
        get() = _navigateToStatePark

    /**
     * Call this immediately after navigating to [StateParkFragment]
     *
     * It will clear the navigation request, so if the user rotates their phone it won't
     * navigate twice.
     */
    // TODO: ask Brad why this has an error?
    fun doneNavigating() {
        _navigateToStatePark.value = null

    }
}