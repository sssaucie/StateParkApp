package com.example.stateparkapp.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stateparkapp.model.dao.StateParksDao
import com.example.stateparkapp.model.entity.StateParks
import kotlinx.coroutines.*

class ParksListViewModel(val database: StateParksDao, application: Application) : AndroidViewModel(application) {

    /**
     * viewModelJob allows us to cancel all coroutines started by this ViewModel.
     */
    private var viewModelJob = Job()

    /**
     * A [CoroutineScope] keeps track of all coroutines started by this ViewModel.
     *
     * Because we pass it [viewModelJob], any coroutine started in this uiScope can be cancelled
     * by calling 'viewModelJob.cancel()'.
     *
     * By default, all coroutines started in uiScope will launch in [Dispatchers.Main] which is
     * the main thread on Android.  This is a sensible default because most coroutines started
     * by a [ViewModel] update the UI after performing some processing.
     *
     * private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
     */

    val parks = database.getAllParks()

    private val _navigateToStateParkDetail = MutableLiveData<StateParks?>()
    val navigateToStateParkDetail
        get() = _navigateToStateParkDetail

    fun onParkDetailClicked(park: StateParks) {
        _navigateToStateParkDetail.value = park
    }

    fun onNavigated() {
        _navigateToStateParkDetail.value = null
        _navigateToHomePage.value = null
    }

    private val _navigateToHomePage = MutableLiveData<Boolean?>()
    val navigateToHomePage: LiveData<Boolean?>
        get() = _navigateToHomePage

    fun onUtahDNRLogoClicked() {
        _navigateToHomePage.value = true
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    private suspend fun update(parks: StateParks) {
        withContext(Dispatchers.IO) {
            database.update(parks)
        }
    }

    private suspend fun insert(parks: StateParks) {
        withContext(Dispatchers.IO) {
            database.insert(parks)
        }
    }

    /**
     * Called when the ViewModel is dismantled.
     * At this point, we want to cancel all coroutines; otherwise, we end up with processes
     * that have nowhere to return to - using memory and resources.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}