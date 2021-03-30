package com.example.stateparkapp.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stateparkapp.model.dao.StateParksDao
import java.lang.IllegalArgumentException

class ParkDetailViewModelFactory (
    private val dataSource: StateParksDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ParkDetailViewModel::class.java)) {
            return ParkDetailViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}