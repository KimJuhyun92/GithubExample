package com.project.githubexample.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orhanobut.logger.Logger
import com.project.githubexample.data.dto.Items
import com.project.githubexample.data.dto.SearchUserModel
import com.project.githubexample.data.repository.GithubRepository
import com.project.githubexample.network.NetworkState
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {

    private val _userList = MutableLiveData<SearchUserModel>()
    val userList: LiveData<SearchUserModel> = _userList

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String> = _searchQuery

    fun searchUsers(id: String, page: Int) = viewModelScope.launch {
        val response = githubRepository.searchUsers(id, page)

        if(response is NetworkState.Success) {
            response.result?.let {
                _userList.value = it
            }
        } else if (response is NetworkState.Failure) {
            Logger.e("Network Response Fail : ${response.throwable?.message}")
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchUsers(id: String): LiveData<PagingData<Items>> {
        return githubRepository.searchUsers(id).cachedIn(viewModelScope)
    }
}