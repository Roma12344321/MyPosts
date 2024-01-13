package com.dev.myposts.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dev.myposts.R
import com.dev.myposts.domain.Post

class PostAdapter : ListAdapter<Post, PostViewHolder>(PostItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layout = when (viewType) {
            MY_POST -> R.layout.post_my_item
            IS_NOT_MY_POST -> R.layout.post_item
            else -> throw RuntimeException("layout is null")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.textViewUserName.text = post.userName
        holder.textViewTitle.text = post.title
        holder.textViewContent.text = post.content
    }

    override fun getItemViewType(position: Int): Int {
        val post = getItem(position)
        return if (post.isItMy) {
            MY_POST
        } else {
            IS_NOT_MY_POST
        }
    }


    companion object {
        private const val MY_POST = 1
        private const val IS_NOT_MY_POST = 2
    }
}