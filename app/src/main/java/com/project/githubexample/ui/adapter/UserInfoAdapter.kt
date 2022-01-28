package com.project.githubexample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.project.githubexample.R
import com.project.githubexample.data.dto.BaseItemModel
import com.project.githubexample.databinding.HolderEventInfoBinding
import com.project.githubexample.databinding.HolderRepoInfoBinding
import com.project.githubexample.databinding.HolderUserInfoBinding
import com.project.githubexample.ui.adapter.holder.BaseViewHolder
import java.lang.IllegalStateException

class UserInfoAdapter : PagingDataAdapter<BaseItemModel, BaseViewHolder>(DiffUtilCallBack()) {

    private companion object {
        const val USER = 0
        const val REPO = 1
        const val EVENT = 2
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is BaseItemModel.UserInfo -> (holder as BaseViewHolder.UserViewHolder).bind(item)
            is BaseItemModel.RepoInfo -> (holder as BaseViewHolder.RepoViewHolder).bind(item)
            is BaseItemModel.EventInfo -> (holder as BaseViewHolder.EventViewHolder).bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is BaseItemModel.UserInfo -> USER
            is BaseItemModel.RepoInfo-> REPO
            is BaseItemModel.EventInfo -> EVENT
            else -> throw IllegalStateException()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            USER -> {
                BaseViewHolder.UserViewHolder(HolderUserInfoBinding.bind(inflater.inflate(R.layout.holder_user_info, parent, false)))
            }
            REPO -> {
                BaseViewHolder.RepoViewHolder(HolderRepoInfoBinding.bind(inflater.inflate(R.layout.holder_repo_info, parent, false)))
            }
            EVENT -> {
                BaseViewHolder.EventViewHolder(HolderEventInfoBinding.bind(inflater.inflate(R.layout.holder_event_info, parent, false)))
            }
            else -> throw IllegalStateException()
        }
    }


    class DiffUtilCallBack : DiffUtil.ItemCallback<BaseItemModel>() {
        override fun areItemsTheSame(oldItem: BaseItemModel, newItem: BaseItemModel): Boolean {
            return when {
                oldItem is BaseItemModel.UserInfo && newItem is BaseItemModel.UserInfo -> oldItem.id == newItem.id
                oldItem is BaseItemModel.RepoInfo && newItem is BaseItemModel.RepoInfo -> oldItem.id == newItem.id
                oldItem is BaseItemModel.EventInfo && newItem is BaseItemModel.EventInfo -> oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: BaseItemModel, newItem: BaseItemModel): Boolean {
            return when {
                oldItem is BaseItemModel.UserInfo && newItem is BaseItemModel.UserInfo -> oldItem == newItem
                oldItem is BaseItemModel.RepoInfo && newItem is BaseItemModel.RepoInfo -> oldItem == newItem
                oldItem is BaseItemModel.EventInfo && newItem is BaseItemModel.EventInfo -> oldItem == newItem
                else -> false
            }
        }
    }
}