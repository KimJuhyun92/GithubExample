package com.project.githubexample.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orhanobut.logger.Logger
import com.project.githubexample.data.datasource.remote.api.GithubApiService
import com.project.githubexample.data.dto.Items
import com.project.githubexample.data.dto.User
import com.project.githubexample.data.paging.UserPagingSource
import com.project.githubexample.data.repository.GithubRepository
import com.project.githubexample.network.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class SearchViewModel @ViewModelInject constructor(
    private val githubRepository: GithubRepository,
    private val githubApiService: GithubApiService
) : ViewModel() {

    private val _userListDto = MutableLiveData<User>()
    val userListDto: LiveData<User> = _userListDto

    fun searchUsers(id: String, page: Int) = viewModelScope.launch {
        val response = githubRepository.searchUsers(id, page)

        if(response is NetworkState.Success) {
            response.result?.let {
                _userListDto.value = it
            }
        } else if (response is NetworkState.Failure) {
            Logger.e("Network Response Fail : ${response.throwable?.message}")
        }
    }

    fun getUsers(id: String): Flow<PagingData<Items>> {
        return githubRepository.getUsers(id).cachedIn(viewModelScope)
    }


//    fun getUsers(id: String): Flow<PagingData<Items>> = Pager(config = PagingConfig(20,enablePlaceholders = false)){
//        UserPagingSource(id, githubApiService)
//    }.flow.cachedIn(viewModelScope)
}