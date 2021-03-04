package com.example.stateparkapp.view.parks_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.stateparkapp.R
import com.example.stateparkapp.model.database.StateParksDatabase
import com.example.stateparkapp.model.entity.StateParks
import com.example.stateparkapp.view_model.ParkNamesViewModelFactory

class ParksDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Get a reference to the binding object and inflate the fragment views.
        val binding: ParkNamesListAdapter = DataBindingUtil.inflate(
            inflater, R.layout.fragment_state_park, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = SleepDetailFragmentArgs.fromBundle(arguments!!)

        // Create an instance of the ViewModel Factory.
        val dataSource = StateParksDatabase.getInstance(application).stateParksDao
        val viewModelFactory = ParkNamesViewModelFactory(arguments.sleepNightKey, dataSource)

        // Get a reference to the ViewModel associated with this fragment.
        val sleepDetailViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(SleepDetailViewModel::class.java)

        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        binding.sleepDetailViewModel = sleepDetailViewModel

        binding.lifecycleOwner = this

        // Add an Observer to the state variable for Navigating when a Quality icon is tapped.
        sleepDetailViewModel.navigateToSleepTracker.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                    SleepDetailFragmentDirections.actionSleepDetailFragmentToSleepTrackerFragment())
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change.
                sleepDetailViewModel.doneNavigating()
            }
        })

        return binding.root

//        return super.onCreateView(inflater, container, savedInstanceState)
    }
}

class ParkNamesFragment(val textView: TextView): RecyclerView.ViewHolder(textView)