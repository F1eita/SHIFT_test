package com.example.shifttest.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.shifttest.R
import com.example.shifttest.data.remote.ResponseStates
import com.example.shifttest.databinding.FragmentUserListBinding
import com.example.shifttest.domain.User
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var adapter: UsersAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by createViewModelLazy(
        UserViewModel::class,
        { this.viewModelStore },
        factoryProducer = { viewModelFactory })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvUsers.adapter = adapter
        binding.rvUsers.addItemDecoration(
            DividerItemDecoration(
                context,
                RecyclerView.VERTICAL
            )
        )
        addObserver()
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshUsers()
            binding.swipeRefreshLayout.isRefreshing = false
        }
        viewModel.getUsers()
    }
    private fun addObserver(){
        viewModel.userLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResponseStates.Success -> {
                    val itemList = mutableListOf<UserItem>()
                    result.data.forEach {
                        itemList.add(UserItem(user = it, onClick = {
                            //TODO(): Открытие полной информации о пользователе
                        }))
                    }
                    adapter.submitList(itemList)
                    binding.viewFlipper.displayedChild = SUCCESS_SCREEN
                }

                is ResponseStates.Failure -> {
                    binding.tvError.text = result.e.message ?: ""
                    binding.viewFlipper.displayedChild = ERROR_SCREEN
                }

                is ResponseStates.Loading -> {
                    binding.viewFlipper.displayedChild = LOADING_SCREEN
                }
            }
        }
    }


    companion object{
        const val LOADING_SCREEN = 0
        const val ERROR_SCREEN = 1
        const val SUCCESS_SCREEN = 2
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}