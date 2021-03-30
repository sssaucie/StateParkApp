package com.example.stateparkapp.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stateparkapp.model.dao.StateParksDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class ParkDetailViewModel(val database: StateParksDao, application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToHomePage = MutableLiveData<Boolean?>()

    private val _navigateToStateParkList = MutableLiveData<Boolean?>()

    val navigateToHomePage: LiveData<Boolean?>
        get() = _navigateToHomePage

    val navigateToStateParkList: LiveData<Boolean?>
        get() = _navigateToStateParkList

    fun onBackButtonClicked() {
        _navigateToStateParkList.value = true
    }

    fun onResetButtonClicked() {
        _navigateToHomePage.value = true
    }

    fun doneNavigating() {
        _navigateToHomePage.value = null
        _navigateToStateParkList.value = null
    }
}