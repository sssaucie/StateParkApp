package com.example.stateparkapp.utilities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.stateparkapp.databinding.ListItemStateParkNamesBinding
import com.example.stateparkapp.model.entity.StateParks

class ParkNamesListAdapter(val clickListener: ParkNamesListClickListener) :
    ListAdapter<StateParks, ParkNamesListAdapter.ParkNamesListViewHolder>(ParkNamesListDiffCallback()) {

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ParkNamesListViewHolder, position: Int) {

        /**
         * Get element from dataset at this position and replace the
         * contents of the view with that element.
         */

        val park = getItem(position)
        holder.bind(clickListener,park)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ParkNamesListViewHolder {
        // Create a new view, which defines the UI of the list item.

        return ParkNamesListViewHolder.from(parent)
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */

    class ParkNamesListViewHolder private constructor(val binding: ListItemStateParkNamesBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(clickListener: ParkNamesListClickListener, item: StateParks) {
                binding.park = item
                binding.clickListener = clickListener
                binding.executePendingBindings()
            }

        companion object {
            fun from(parent: ViewGroup): ParkNamesListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemStateParkNamesBinding.inflate(
                    layoutInflater, parent, false)
                return ParkNamesListViewHolder(binding)
            }
        }
        }

//    class ParkNamesListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val textView: TextView
//
//        init {
//            // Define click listener for the ViewHolder's View.
//            textView = view.findViewById((R.id.park_name))
//        }
//    }

    // Return the size of dataset (invoked by the layout manager)
//    override fun getItemCount() = getItem().name.count()
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between an old list and a new
 * list that has been passed to 'submitList'.
 */

class ParkNamesListDiffCallback : DiffUtil.ItemCallback<StateParks>() {
    override fun areItemsTheSame(oldItem: StateParks, newItem: StateParks): Boolean {
        return oldItem.parksId == newItem.parksId
    }

    override fun areContentsTheSame(oldItem: StateParks, newItem: StateParks): Boolean {
        return oldItem == newItem
    }
}

class ParkNamesListClickListener(val clickListener: (parkName: String) -> Unit) {
    fun onClick(name: StateParks) = clickListener(name.name)
}