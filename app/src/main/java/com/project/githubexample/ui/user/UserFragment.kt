package com.project.githubexample.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.project.githubexample.R
import com.project.githubexample.databinding.FragmentUserBinding
import com.project.githubexample.ui.adapter.UserInfoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {
    private val viewModel by viewModels<UserViewModel>()
    private lateinit var binding: FragmentUserBinding
    private lateinit var userInfoAdapter: UserInfoAdapter

    private val args: UserFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user,
            container,
            false
        )
        binding.lifecycleOwner = this@UserFragment

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSwipeRefresh()

        setupBackButton()

        setUserInfoAdapter()

        setUserEvents()
    }

    private fun setUserInfoAdapter() {
        userInfoAdapter = UserInfoAdapter()
        binding.rvUser.adapter = userInfoAdapter
    }

    private fun setUserEvents() {
        viewModel.getUserEvents(args.userId).observe(viewLifecycleOwner) { eventData ->
            userInfoAdapter.submitData(lifecycle, eventData)
        }
    }

    private fun setupSwipeRefresh() {
        binding.refreshLayout.setOnRefreshListener {
            setUserEvents()
            binding.refreshLayout.isRefreshing = false
        }
    }

    private fun setupBackButton() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })
    }
}