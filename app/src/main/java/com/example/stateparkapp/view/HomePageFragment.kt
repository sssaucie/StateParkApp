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
import com.example.stateparkapp.databinding.HomeLandingPageBinding
import com.example.stateparkapp.model.database.StateParksDatabase
import com.example.stateparkapp.view_model.HomePageViewModel
import com.example.stateparkapp.view_model.HomePageViewModelFactory

class HomePageFragment : Fragment() {
    private val viewModel : HomePageViewModel by lazy {
        ViewModelProvider(this).get(HomePageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : HomeLandingPageBinding = DataBindingUtil.inflate(
            inflater, R.layout.home_landing_page, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = StateParksDatabase.getInstance(application).stateParksDao()

        val viewModelFactory = HomePageViewModelFactory(dataSource, application)

        val homePageViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(HomePageViewModel::class.java)

        binding.viewModel = homePageViewModel

        binding.setLifecycleOwner(this)

        homePageViewModel.navigateToStateParkList.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                this.findNavController().navigate(
                    HomePageFragmentDirections
                        .actionHomePageFragmentToParksListFragment())
                homePageViewModel.onParkButtonClicked()
            }
        })

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}