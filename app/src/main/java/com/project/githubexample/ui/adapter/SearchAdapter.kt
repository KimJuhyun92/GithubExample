package com.project.githubexample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.githubexample.data.dto.Items
import com.project.githubexample.databinding.ItemSearchBinding
import com.project.githubexample.ui.home.SearchFragmentDirections

class SearchAdapter : PagingDataAdapter<Items,SearchAdapter.UserViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val searchResult = getItem(position)
        if (searchResult != null) {
            holder.binds(searchResult)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    class UserViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binds(item: Items) {
            binding.apply {
                searchResult = item
                binding.setClickListener {
                    it.findNavController().navigate(SearchFragmentDirections.actionNavSearchToUser(item.login))
                }
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