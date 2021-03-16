package com.example.stateparkapp.view.park_names

//import android.view.LayoutInflater
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.stateparkapp.R
//import com.example.stateparkapp.model.entity.StateParks
//
//abstract class ParkNamesListAdapter: RecyclerView.Adapter<ParkNamesFragment>() {
//    var data = listOf<StateParks>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }
//
//    override fun getItemCount() = data.size
//
//    override fun onBindViewHolder(holder: ParkNamesFragment, position: Int) {
//        val item = data[position]
//        holder.textView.text = item.parksId.toString()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkNamesFragment {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val view = layoutInflater.inflate(R.layout.fragment_state_park, parent, false) as TextView
//
//        return ParkNamesFragment(view)
//    }
//}