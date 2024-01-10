package com.dev.myposts.presentation.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev.myposts.R

class PostViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
    val textViewUserName = view.findViewById<TextView>(R.id.textViewUserName)
    val textViewTitle = view.findViewById<TextView>(R.id.textViewTitle)
    val textViewContent = view.findViewById<TextView>(R.id.textViewContent)
}