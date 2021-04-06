package com.example.stateparkapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.stateparkapp.R
import com.example.stateparkapp.databinding.FragmentParkDetailBinding
import com.example.stateparkapp.model.entity.StateParks
import com.example.stateparkapp.view_model.ParkDetailViewModel
import com.example.stateparkapp.view_model.ParkDetailViewModelFactory

class ParkDetailFragment : Fragment() {

    private lateinit var selectedPark : StateParks

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        selectedPark = ParkDetailFragmentArgs.fromBundle(requireArguments()).selectedPark

        val binding : FragmentParkDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_park_detail, container, false)

        val viewModelFactory = ParkDetailViewModelFactory(selectedPark)

        val parkDetailViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(ParkDetailViewModel::class.java)

        binding.viewModel = parkDetailViewModel

        binding.detailScroll.viewModel = parkDetailViewModel

        binding.setLifecycleOwner(this)

        parkDetailViewModel.navigateToHomePage.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    ParkDetailFragmentDirections.actionParkDetailFragmentToHomePageFragment())
                parkDetailViewModel.doneNavigating()
            }
        })

        parkDetailViewModel.navigateToStateParkList.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    ParkDetailFragmentDirections.actionParkDetailFragmentToParksListFragment())
                parkDetailViewModel.doneNavigating()
            }
        })

        parkDetailViewModel.navigateToMaps.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    ParkDetailFragmentDirections.actionParkDetailFragmentToMapsFragment())
                parkDetailViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}