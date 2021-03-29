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
import com.example.stateparkapp.databinding.FragmentParkNamesListBinding
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

        val dataSource = StateParksDatabase.getInstance(application).stateParksDao

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
                        .action_parksListFragment_to_parksDetailFragment(park))
                parksListViewModel.onParkDetailNavigated()
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

        val adapter = ParksListAdapter(ParksListClickListener { parkName ->
            parksListViewModel.onParkDetailClicked(parkName)
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

//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.databinding.DataBindingUtil
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import androidx.navigation.fragment.findNavController
//import androidx.recyclerview.widget.RecyclerView
//import com.example.stateparkapp.R
//import com.example.stateparkapp.model.database.StateParksDatabase
//import com.example.stateparkapp.view_model.ParksListViewModelFactory
//
//class ParksDetailFragment : Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Get a reference to the binding object and inflate the fragment views.
//        val binding: ParksListAdapter = DataBindingUtil.inflate(
//            inflater, R.layout.fragment_state_park, container, false)
//
//        val application = requireNotNull(this.activity).application
//        val arguments = SleepDetailFragmentArgs.fromBundle(arguments!!)
//
//        // Create an instance of the ViewModel Factory.
//        val dataSource = StateParksDatabase.getInstance(application).stateParksDao
//        val viewModelFactory = ParksListViewModelFactory(arguments.sleepNightKey, dataSource)
//
//        // Get a reference to the ViewModel associated with this fragment.
//        val sleepDetailViewModel =
//            ViewModelProvider(
//                this, viewModelFactory).get(SleepDetailViewModel::class.java)
//
//        // To use the View Model with data binding, you have to explicitly
//        // give the binding object a reference to it.
//        binding.sleepDetailViewModel = sleepDetailViewModel
//
//        binding.lifecycleOwner = this
//
//        // Add an Observer to the state variable for Navigating when a Quality icon is tapped.
//        sleepDetailViewModel.navigateToSleepTracker.observe(viewLifecycleOwner, Observer {
//            if (it == true) { // Observed state is true.
//                this.findNavController().navigate(
//                    SleepDetailFragmentDirections.actionSleepDetailFragmentToSleepTrackerFragment())
//                // Reset state to make sure we only navigate once, even if the device
//                // has a configuration change.
//                sleepDetailViewModel.doneNavigating()
//            }
//        })
//
//        return binding.root
//
////        return super.onCreateView(inflater, container, savedInstanceState)
//    }
//}
//
//class ParkNamesFragment(val textView: TextView): RecyclerView.ViewHolder(textView)