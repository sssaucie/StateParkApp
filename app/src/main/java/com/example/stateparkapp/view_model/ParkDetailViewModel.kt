package com.example.stateparkapp.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stateparkapp.model.entity.StateParks

class ParkDetailViewModel(park: StateParks) : ViewModel() {

    private val _selectedPark = MutableLiveData<StateParks>()

    val selectedPark : LiveData<StateParks>
            get() = _selectedPark

    init {
        _selectedPark.value = park
    }

    private val _navigateToHomePage = MutableLiveData<Boolean?>()

    private val _navigateToStateParkList = MutableLiveData<Boolean?>()

    private val _navigateToMaps = MutableLiveData<Boolean?>()

    val navigateToHomePage: LiveData<Boolean?>
        get() = _navigateToHomePage

    val navigateToStateParkList: LiveData<Boolean?>
        get() = _navigateToStateParkList

    val navigateToMaps: LiveData<Boolean?>
        get() = _navigateToMaps

    fun onBackButtonClicked() {
        _navigateToStateParkList.value = true
    }

    fun onDNRLogoClicked() {
        _navigateToHomePage.value = true
    }

    fun onMapsClicked() {
        _navigateToMaps.value = true
    }

    fun doneNavigating() {
        _navigateToHomePage.value = null
        _navigateToStateParkList.value = null
        _navigateToMaps.value = null
    }
}