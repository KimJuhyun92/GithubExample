package com.project.githubexample.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.project.githubexample.R
import com.project.githubexample.databinding.FragmentSearchBinding
import com.project.githubexample.ui.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val viewModel by viewModels<SearchViewModel>()
    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

        setupSwipeRefresh()

        setupSearchBar()

        setupSearchAdapter()

        setupHideKeyboard()
    }

    @SuppressLint("ClickableViewAccessibility")
    fun setupHideKeyboard(){
        binding.rvUser.setOnTouchListener { view, event ->
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
        }
    }

    private fun setupSearchAdapter() {
        searchAdapter = SearchAdapter()
        binding.rvUser.adapter = searchAdapter
    }

    private fun setupSwipeRefresh() {
        binding.refreshLayout.setOnRefreshListener {
            viewModel.searchQuery.value?.let {
                getSearchResult(it)
            }
            binding.refreshLayout.isRefreshing = false
        }
    }

    private fun setupSearchBar() {
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.setSearchQuery(it)
                    getSearchResult(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun getSearchResult(query: String) {
        viewModel.searchUsers(query).observe(viewLifecycleOwner) { userData ->
            searchAdapter.submitData(lifecycle, userData)
        }
    }
}