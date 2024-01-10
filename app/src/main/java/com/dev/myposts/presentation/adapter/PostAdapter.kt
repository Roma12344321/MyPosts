package com.dev.myposts.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dev.myposts.R
import com.dev.myposts.domain.Post
import com.dev.myposts.presentation.adapter.PostItemDiffUtil
import com.dev.myposts.presentation.adapter.PostViewHolder

class PostAdapter : ListAdapter<Post, PostViewHolder>(PostItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.textViewUserName.text = post.userName
        holder.textViewTitle.text = post.title
        holder.textViewContent.text = post.content
    }
}