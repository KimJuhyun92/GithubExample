package com.project.githubexample.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.orhanobut.logger.Logger
import com.project.githubexample.R
import com.project.githubexample.databinding.FragmentSearchBinding
import com.project.githubexample.ui.adapter.UserAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val viewModel by viewModels<SearchViewModel>()
    private lateinit var binding: FragmentSearchBinding
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search,
            container,
            false
        )
        binding.lifecycleOwner = this@SearchFragment

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refresh()

        setSearchBar()

        setUserAdapter()

    }

    private fun setUserAdapter() {
        val viewModel = this.viewModel
        if (viewModel != null) {
            userAdapter = UserAdapter()
            binding.rvUser.adapter = userAdapter
        } else {
            Logger.w("ViewModel not initialized when attempting to set up adapter.")
        }
    }

    private fun refresh() {
        binding.refreshLayout.setOnRefreshListener {
            binding.refreshLayout.isRefreshing = false
        }
    }

    private fun setSearchBar() {
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

//                query?.let { viewModel.searchUsers(query, 1) }
                lifecycleScope.launch {
                    query?.let {
                        viewModel.getUsers(it).collect { userData ->
                            userAdapter.submitData(userData)
                        }
                    }
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }
}