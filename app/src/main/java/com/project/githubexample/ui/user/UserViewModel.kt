package com.project.githubexample.ui.user

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orhanobut.logger.Logger
import com.project.githubexample.data.dto.BaseItemModel
import com.project.githubexample.data.repository.GithubRepository
import com.project.githubexample.network.NetworkState
import kotlinx.coroutines.launch


class UserViewModel @ViewModelInject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {

    private val _userInfo = MutableLiveData<BaseItemModel>()
    val userInfo: LiveData<BaseItemModel> = _userInfo

    private val _repoInfoList = MutableLiveData<List<BaseItemModel>>()
    val repoInfoList: LiveData<List<BaseItemModel>> = _repoInfoList


    fun getUserInfo(id: String) = viewModelScope.launch {
        val response = githubRepository.getUserInfo(id)

        if (response is NetworkState.Success) {
            response.result?.let {
                _userInfo.value = it
            }
        } else if (response is NetworkState.Failure) {
            Logger.e("Network Response Fail : ${response.throwable?.message}")
        }
    }

    fun getUserRepos(id: String) = viewModelScope.launch {
        val response = githubRepository.getUserRepos(id)

        if (response is NetworkState.Success) {
            response.result?.let {
                _repoInfoList.value = it
            }
        } else if (response is NetworkState.Failure) {
            Logger.e("Network Response Fail : ${response.throwable?.message}")
        }
    }

    fun getUserEvents(id: String): LiveData<PagingData<BaseItemModel>> {
        return githubRepository.getUserEvents(id).cachedIn(viewModelScope)
    }
}