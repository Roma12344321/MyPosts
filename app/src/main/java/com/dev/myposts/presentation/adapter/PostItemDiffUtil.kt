package com.dev.myposts.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.dev.myposts.domain.Post

class PostItemDiffUtil : DiffUtil.ItemCallback<Post>() {

    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}