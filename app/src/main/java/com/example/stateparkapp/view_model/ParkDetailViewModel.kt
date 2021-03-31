package com.example.stateparkapp.view_model

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stateparkapp.model.entity.StateParks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class ParkDetailViewModel(park: StateParks) : ViewModel() {

    private val _selectedPark = MutableLiveData<StateParks>()

    val selectedPark : LiveData<StateParks>
            get() = _selectedPark

    init {
        _selectedPark.value = park
    }

    private val _navigateToHomePage = MutableLiveData<Boolean?>()

    private val _navigateToStateParkList = MutableLiveData<Boolean?>()

    val navigateToHomePage: LiveData<Boolean?>
        get() = _navigateToHomePage

    val navigateToStateParkList: LiveData<Boolean?>
        get() = _navigateToStateParkList

    fun onBackButtonClicked() {
        _navigateToStateParkList.value = true
    }

    fun onDNRLogoClicked() {
        _navigateToHomePage.value = true
    }

    fun doneNavigating() {
        _navigateToHomePage.value = null
        _navigateToStateParkList.value = null
    }
}