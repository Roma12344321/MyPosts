package com.dev.myposts.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dev.myposts.R
import com.dev.myposts.domain.Post

class MyPostAdapter : ListAdapter<Post,MyPostViewHolder>(PostItemDiffUtil()){

    var onItemClickListener : OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_person_post_item, parent, false)
        return MyPostViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyPostViewHolder, position: Int) {
        val post = getItem(position)
        holder.textViewUserName.text = post.userName
        holder.textViewTitle.text = post.title
        holder.textViewContent.text = post.content
        holder.imageView.setOnClickListener {
            onItemClickListener?.onItemClick(post.id!!)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(id : Int)
    }
}