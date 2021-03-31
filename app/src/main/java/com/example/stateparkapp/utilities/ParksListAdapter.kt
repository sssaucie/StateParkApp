package com.example.stateparkapp.utilities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.stateparkapp.databinding.ListItemStateParkNamesBinding
import com.example.stateparkapp.model.entity.StateParks

class ParksListAdapter(val clickListener: ParksListClickListener) :
    ListAdapter<StateParks, ParksListAdapter.ParksListViewHolder>(ParkNamesListDiffCallback()) {

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ParksListViewHolder, position: Int) {

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
    ): ParksListViewHolder {
        // Create a new view, which defines the UI of the list item.

        return ParksListViewHolder.from(parent)
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */

    class ParksListViewHolder private constructor(val binding: ListItemStateParkNamesBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: ParksListClickListener, item: StateParks) {
            binding.park = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ParksListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemStateParkNamesBinding.inflate(
                    layoutInflater, parent, false)
                return ParksListViewHolder(binding)
            }
        }
    }
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

class ParksListClickListener(val clickListener: (park: StateParks) -> Unit) {
    fun onClick(park: StateParks) = clickListener(park)
}