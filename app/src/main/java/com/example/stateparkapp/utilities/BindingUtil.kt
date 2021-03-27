package com.example.stateparkapp.utilities

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * ViewHolder that holds a single [TextView].
 *
 * A ViewHolder holds a view for the [RecyclerView] as well as providing additional information
 * to the RecyclerView, such as where on the screen it was last drawn during scrolling.
 */

class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)