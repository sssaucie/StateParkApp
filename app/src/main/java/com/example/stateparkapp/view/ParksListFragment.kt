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
import androidx.recyclerview.widget.GridLayoutManager
import com.example.stateparkapp.R
import com.example.stateparkapp.databinding.FragmentParksListBinding
import com.example.stateparkapp.model.database.StateParksDatabase
import com.example.stateparkapp.utilities.ParksListAdapter
import com.example.stateparkapp.utilities.ParksListClickListener
import com.example.stateparkapp.view_model.ParksListViewModel
import com.example.stateparkapp.view_model.ParksListViewModelFactory

class ParksListFragment : Fragment() {
    private val viewModel : ParksListViewModel by lazy {
        ViewModelProvider(this).get(ParksListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : FragmentParksListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_parks_list, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = StateParksDatabase.getInstance(application).stateParksDao()

        val viewModelFactory = ParksListViewModelFactory(dataSource, application)

        val parksListViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(ParksListViewModel::class.java)

        binding.viewModel = parksListViewModel

        binding.setLifecycleOwner(this)

        parksListViewModel.navigateToStateParkDetail.observe(viewLifecycleOwner, Observer { park ->
            park?.let {
                this.findNavController().navigate(
                    ParksListFragmentDirections
                        .actionParksListFragmentToParkDetailFragment(park))
                parksListViewModel.onNavigated()
            }
        })

        parksListViewModel.navigateToHomePage.observe(viewLifecycleOwner, Observer { park ->
            park?.let {
                this.findNavController().navigate(
                    ParksListFragmentDirections
                        .actionParksListFragmentToHomePageFragment())
                parksListViewModel.onNavigated()
            }
        })

        /**
         * Grid layout
         */

        val manager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) = when(position) {
                0 -> 2
                else -> 1
            }
        }

        val adapter = ParksListAdapter(ParksListClickListener { id ->
            parksListViewModel.onParkDetailClicked(id)
        })

        binding.stateParksList.adapter = adapter

        binding.stateParksList.layoutManager = manager

        /**
         * Add the view adapter and observe for new data
         */

        parksListViewModel.parks.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}