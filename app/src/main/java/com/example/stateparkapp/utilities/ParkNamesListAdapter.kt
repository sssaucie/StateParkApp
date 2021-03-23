package com.example.stateparkapp.utilities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.stateparkapp.R
import com.example.stateparkapp.databinding.ListItemStateParkNamesBinding
import com.example.stateparkapp.model.entity.StateParks

class ParkNamesListAdapter(val clickListener: ParkNamesListListener) : ListAdapter<StateParks, ParkNamesListAdapter.ParkNamesListViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<StateParks>() {
        override fun areItemsTheSame(oldItem: StateParks, newItem: StateParks): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: StateParks, newItem: StateParks): Boolean {
            return oldItem == newItem
        }
    }

    class ParkNamesListViewHolder(private var binding: ListItemStateParkNamesBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: ParkNamesListListener, name: StateParks) {
            binding.parkName = name
            binding.clickListener = listener
            binding.executePendingBindings()
        }

        companion object {
            fun create(parent: ViewGroup) : ParkNamesListViewHolder {
                val view : View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_park_names_list, parent, false)
                return ParkNamesListViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkNamesListViewHolder {
        return ParkNamesListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ParkNamesListViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position))
    }
}

class ParkNamesListListener(val clickListener: (park: StateParks) -> Unit) {
    fun onClick(park: StateParks) = clickListener(park)
}

//private const val ITEM_VIEW_TYPE_HEADER = 0
//private const val ITEM_VIEW_TYPE_ITEM = 1
//
//class ParkNamesListAdapter(val clickListener : ParkNamesListListener) : ListAdapter<DataItem, RecyclerView.ViewHolder>(StateParkDiffCallback()) {
//
//    private val adapterScope = CoroutineScope((Dispatchers.Default))
//
//    /**
//     * Part of the RecyclerView adapter, called when RecyclerView needs a new [ViewHolder].
//     *
//     * A ViewHolder holds a view for the [RecyclerView] as well as providing additional information
//     * to the RecyclerView, such as where on the screen it was last drawn during scrolling.
//     */
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
////        TODO figure out why this is giving an error
////        when (holder) {
////            is ViewHolder -> {
////                val parkItem = getItem(position) as DataItem.ParkItem
////                holder.bind(parkItem.parkName, clickListener)
////            }
////        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return when (viewType) {
//            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
//            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
//            else -> throw ClassCastException("Unknown viewType ${viewType}")
//        }
//    }
//
//
//    class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        companion object {
//            fun from(parent: ViewGroup): TextViewHolder {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                val view = layoutInflater.inflate(R.layout.list_item_state_park_names, parent, false)
//                return TextViewHolder(view)
//            }
//        }
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return super.getItemViewType(position)
//    }
//
//    fun addHeaderAndSubmitList(list: List<StateParks>?) {
//        adapterScope.launch {
//            val items = when (list) {
//                null -> listOf(DataItem.Header)
//                else -> listOf(DataItem.Header) + list.map { DataItem.ParkItem(it) }
//            }
//            withContext(Dispatchers.Main) {
//                submitList(items)
//            }
//        }
//    }
//
//    class ViewHolder private constructor(val binding: ListItemStateParkNamesBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(listener: ParkNamesListListener, parkName: ParkNamesListAdapter) {
//            // TODO: figure out why this is giving an error
////            binding.getParkName() = parkName
////            binding.clickListener = listener
//            binding.executePendingBindings()
//        }
//
//        companion object {
//            fun from(parent: ViewGroup): ViewHolder {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                val binding = ListItemStateParkNamesBinding.inflate(layoutInflater, parent, false)
//                return ViewHolder(binding)
//            }
//        }
//    }
//}
//
//class StateParkDiffCallback : DiffUtil.ItemCallback<DataItem>() {
//    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
//        return oldItem.id == newItem.id
//    }
//
//    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
//        return oldItem == newItem
//    }
//}
//
//class ParkNamesListListener(val clickListener: (parkId: Long) -> Unit) {
//    fun onClick(name: StateParks) = clickListener(name.parksId)
//}
//
///**
// * Adding a header
// */
//
//sealed class DataItem {
//    data class ParkItem(val park: StateParks): DataItem() {
//        override val id: String = park.name
//    }
//
//    object Header: DataItem() {
//        override val id = String.toString()
//    }
//
//    abstract val id: String
//}
