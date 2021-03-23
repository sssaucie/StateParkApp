package com.example.stateparkapp.view.park_names

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.stateparkapp.R
import com.example.stateparkapp.databinding.FragmentParkNamesListBinding
import com.example.stateparkapp.model.database.StateParksDatabase
import com.example.stateparkapp.utilities.ParkNamesListAdapter
import com.example.stateparkapp.utilities.ParkNamesListListener
import com.example.stateparkapp.view_model.ParkNamesListViewModel
import com.example.stateparkapp.view_model.ParkNamesListViewModelFactory

class ParkNamesListFragment : Fragment() {
    private val viewModel : ParkNamesListViewModel by lazy {
        ViewModelProvider(this).get(ParkNamesListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentParkNamesListBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel

        val adapter = ParkNamesListAdapter(ParkNamesListListener { parkId ->
            val destination = R.layout.fragment_detail_state_park
        })

        val application = requireNotNull(this.activity).application

        val dataSource = StateParksDatabase.getInstance(application).stateParksDao()

        val viewModelFactory = ParkNamesListViewModelFactory(dataSource, application)

        val parkNamesListViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(ParkNamesListViewModel::class.java)

        // TODO: figure out why this is giving an error
//        binding.parkNamesListViewModel = parkNamesListViewModel

        /**
         * Grid layout
         */

        val manager = GridLayoutManager(activity, 2)
        object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) = when(position) {
                0 -> 2
                else -> 1
            }
        }

        binding.stateParksList.layoutManager = manager

        /**
         * Add the view adapter and observe for new data
         */

        return super.onCreateView(inflater, container, savedInstanceState)
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
//import com.example.stateparkapp.view_model.ParkNamesListViewModelFactory
//
//class ParksDetailFragment : Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Get a reference to the binding object and inflate the fragment views.
//        val binding: ParkNamesListAdapter = DataBindingUtil.inflate(
//            inflater, R.layout.fragment_state_park, container, false)
//
//        val application = requireNotNull(this.activity).application
//        val arguments = SleepDetailFragmentArgs.fromBundle(arguments!!)
//
//        // Create an instance of the ViewModel Factory.
//        val dataSource = StateParksDatabase.getInstance(application).stateParksDao
//        val viewModelFactory = ParkNamesListViewModelFactory(arguments.sleepNightKey, dataSource)
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