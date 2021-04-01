package com.example.stateparkapp.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stateparkapp.model.dao.StateParksDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class HomePageViewModel(val database: StateParksDao, application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToStateParkList = MutableLiveData<Boolean?>()

    /**
     * If this is non-null, immediately navigate to [StateParkFragment] and call [doneNavigating]
     */
    val navigateToStateParkList: LiveData<Boolean?>
        get() = _navigateToStateParkList

    fun onParkButtonClicked() {
        _navigateToStateParkList.value = true
    }

    /**
     * Call this immediately after navigating to [StateParkFragment]
     *
     * It will clear the navigation request, so if the user rotates their phone it won't
     * navigate twice.
     */
    // TODO: ask Brad why this has an error?
    fun doneNavigating() {
        _navigateToStateParkList.value = null
    }
}