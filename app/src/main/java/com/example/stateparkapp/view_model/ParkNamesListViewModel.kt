package com.example.stateparkapp.view_model

//import androidx.hilt.lifecycle.ViewModelInject
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MediatorLiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.example.stateparkapp.model.dao.StateParksDao
//import com.example.stateparkapp.model.entity.StateParks
//import com.example.stateparkapp.network.StateParksRepository
//
//class ParkNamesListViewModel @ViewModelInject internal constructor(
//    parksRepository: StateParksRepository,
//    private val parksKey: Long = 0L,
//    dataSource: StateParksDao
//) : ViewModel() {
//
//    /**
//     * Hold a reference to StateParksDatabase via its StateParksDao.
//     */
//    private val database = dataSource
//
//
//    /**
//     */
//
//    private val parks = LiveData<StateParks>()
//
//    fun getParks() = parks
//
//    init {
//        parks.addSource(database.insert(parksKey), parks::setValue)
//    }
//
//    /**
//     * Variable that tells the fragment whether it should navigate to [ParkNamesFragment].
//     *
//     * This is `private` because we don't want to expose the ability to set [MutableLiveData] to
//     * the [Fragment]
//     */
//    private val _navigateToStatePark = MutableLiveData<Boolean?>()
//
//    /**
//     * When true immediately navigate back to the [ParkNamesFragment]
//     */
//    val navigateToStatePark: LiveData<Boolean?>
//        get() = _navigateToStatePark
//
//    /**
//     *
//     */
//
//
//    /**
//     * Call this immediately after navigating to [ParkNamesFragment]
//     */
//    fun doneNavigating() {
//        _navigateToStatePark.value = null
//    }
//
//    fun onClose() {
//        _navigateToStatePark.value = true
//    }
//
//}