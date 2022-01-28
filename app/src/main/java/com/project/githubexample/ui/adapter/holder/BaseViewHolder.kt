package com.project.githubexample.ui.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.project.githubexample.data.dto.BaseItemModel
import com.project.githubexample.databinding.HolderEventInfoBinding
import com.project.githubexample.databinding.HolderRepoInfoBinding
import com.project.githubexample.databinding.HolderUserInfoBinding

sealed class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    //TODO 이름 없을 시 아이디값으로 대체
    class UserViewHolder(binding: HolderUserInfoBinding): BaseViewHolder(binding.root) {
        private val userInfoBinding = binding
        fun bind(item: BaseItemModel.UserInfo) {
            userInfoBinding.userInfo = item
        }
    }

    class RepoViewHolder(binding: HolderRepoInfoBinding): BaseViewHolder(binding.root) {
        private val repoInfoBinding = binding
        fun bind(item: BaseItemModel.RepoInfo) {
            repoInfoBinding.repoInfo = item
        }
    }

    class EventViewHolder(binding: HolderEventInfoBinding): BaseViewHolder(binding.root) {
        private val eventInfoBinding = binding
        fun bind(item: BaseItemModel.EventInfo) {
            eventInfoBinding.event = item
        }
    }

}