package com.example.stateparkapp.view_model

//class ParkNamesListViewModel(
//    val stateParksDao: StateParksDao,
//    application: Application) : AndroidViewModel(application) {
//    /**
//     * viewModelJob allows us to cancel all coroutines started by this ViewModel.
//
//    private var viewModelJob = Job()
//
//    /**
//     * A [CoroutineScope] keeps track of all coroutines started by this ViewModel.
//     *
//     * Because we pass it [viewModelJob], any coroutine started in this uiScope can be cancelled
//     * by calling `viewModelJob.cancel()`
//     *
//     * By default, all coroutines started in uiScope will launch in [Dispatchers.Main] which is
//     * the main thread on Android. This is a sensible default because most coroutines started by
//     * a [ViewModel] update the UI after performing some processing.
//    */
//    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
//     */
//
//    private var stateParkName = MutableLiveData<StateParks>()
//
//    val parks = stateParksDao.getAll()
//
//    /**
//     * If Parks menu is selected, the ARROW is visible.
//     */
//
//    val arrowVisible = Transformations.map(stateParkName) {
//        null != it
//    }
//
//    /**
//     * If Parks menu is not selected, the ARROW is not visible.
//     */
//
//    val arrowInvisible = Transformations.map(stateParkName) {
//        null == it
//    }
//
//    /**
//     * Variable that tells the Fragment to navigate to a specific [StateParkFragment]
//     *
//     * This is private because we don't want to expose setting this value to the Fragment.
//     */
//
//    private val _navigateToSelectedPark = MutableLiveData<StateParks>()
//
//    val navigateToSelectedPark : LiveData<StateParks>
//        get() = _navigateToSelectedPark
//
//    /**
//     * Call this immediately after navigating to [StateParkFragment]
//     *
//     * it will clear the navigation request, so if the user rotates their phone it won't
//     * navigate twice.
//     */
//
//    fun doneNavigating() {
//        _navigateToSelectedPark.value = null
//    }
//
////    fun onStateParkClicked(id: Long) {
////        _navigateToSelectedPark.value = id
////    }
//
//    fun onStateParkNavigated() {
//        _navigateToSelectedPark.value = null
//    }
//
//    init {
//        initializeStatePark()
//    }
//
//    private fun initializeStatePark() {
//        viewModelScope.launch {
//        }
//    }
//
//    private suspend fun clear() {
//        withContext(Dispatchers.IO) {
//            stateParksDao.clear()
//        }
//    }
//
//}

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stateparkapp.model.entity.StateParks
import com.example.stateparkapp.network.StateParksRepository
import kotlinx.coroutines.launch

class ParkNamesListViewModel(private val repository: StateParksRepository) : ViewModel() {

    /**
     * Using LiveData and caching what allParks returns has several benefits:
     * - We can put an observer on the data (instead of polling for changes) and only update
     *   the UI when the data actually changes.
     * - Repository is completely separated from the UI through the ViewModel.
     */

    val allParks: LiveData<List<StateParks>> = repository.allParks

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */

    fun insert(parks: StateParks) = viewModelScope.launch {
        repository.insert(parks)
    }
}