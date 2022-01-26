package com.project.githubexample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.githubexample.data.dto.Items
import com.project.githubexample.databinding.ItemUserBinding

class UserAdapter : PagingDataAdapter<Items,UserAdapter.UserViewHolder>(DiffUtilCallBack()) {


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.binds(user)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binds(userInfo: Items) {
            binding.apply {
                user = userInfo

                Glide
                    .with(this.root)
                    .load(userInfo.avatarUrl)
                    .into(thumbnail)

                executePendingBindings()
            }
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Items>() {
        override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean =
            oldItem.login == newItem.login

        override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean =
            oldItem == newItem
    }
}