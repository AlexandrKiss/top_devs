package ua.kiss.topdevs.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ua.kiss.topdevs.databinding.UserListFragmentBinding
import ua.kiss.topdevs.models.ApiUser
import ua.kiss.topdevs.ui.adapters.UserListAdapter
import ua.kiss.topdevs.other.ClickCallback
import ua.kiss.topdevs.other.Status

@AndroidEntryPoint
class UserListFragment: BaseFragment() {
    private val binding by lazy { UserListFragmentBinding.inflate(layoutInflater) }
    private val adapter = UserListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View { return binding.root }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureRecyclerView()
        getUserList()
    }

    private fun getUserList() {
        viewModel.getUserListLiveData().observe(this, { res ->
            when (res.status) {
                Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    res.data?.let { list ->
                        adapter.setList(list)
                        binding.userListFragmentRecyclerView.adapter = adapter
                    }
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    res.message?.let { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    private fun configureRecyclerView() {
        binding.userListFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.attachCallback(object : ClickCallback {
            override fun <T> onClick(data: T?) {
                val apiUser = data as ApiUser
                viewModel.saveUser(apiUser)
                val action = UserListFragmentDirections
                        .actionUserListFragmentToUserInfoFragment(apiUser.id)
                findNavController().navigate(action)
            }
        })
    }
}