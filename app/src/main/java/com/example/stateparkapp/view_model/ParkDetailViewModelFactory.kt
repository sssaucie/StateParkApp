package com.example.stateparkapp.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stateparkapp.model.entity.StateParks

class ParkDetailViewModelFactory (
    private val park: StateParks
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ParkDetailViewModel::class.java)) {
            return ParkDetailViewModel(park) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}