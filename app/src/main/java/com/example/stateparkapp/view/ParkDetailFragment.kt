package com.example.stateparkapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.stateparkapp.R
import com.example.stateparkapp.model.database.StateParksDatabase
import com.example.stateparkapp.view_model.ParkDetailViewModel
import com.example.stateparkapp.view_model.ParkDetailViewModelFactory

class ParkDetailFragment : Fragment() {
    private val viewModel: ParkDetailViewModel by lazy {
        ViewModelProvider(this).get(ParkDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : FragmentParkDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_park_detail, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = StateParksDatabase.getInstance(application).stateParksDao()

        val viewModelFactory = ParkDetailViewModelFactory(dataSource, application)

        val parkDetailViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(ParkDetailViewModel::class.java)

        binding.viewModel = parkDetailViewModel

        binding.setLifecycleOwner(this)

        parkDetailViewModel.navigateToHomePage.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    ParkDetailFragmentDirections.actionParkDetailFragmentToHomePageFragment())
                parkDetailViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}