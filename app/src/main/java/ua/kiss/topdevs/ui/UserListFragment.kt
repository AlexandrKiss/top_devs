package ua.kiss.topdevs.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import ua.kiss.topdevs.databinding.UserListFragmentBinding
import ua.kiss.topdevs.network.ApiHelperImpl
import ua.kiss.topdevs.network.RetrofitBuilder
import ua.kiss.topdevs.ui.adapters.UserListAdapter
import ua.kiss.topdevs.utils.ClickCallback
import ua.kiss.topdevs.utils.Status
import ua.kiss.topdevs.utils.ViewModelFactory
import ua.kiss.topdevs.viewmodels.UserViewModel

class UserListFragment: Fragment() {
    private val binding by lazy { UserListFragmentBinding.inflate(layoutInflater) }
    private val viewModel by lazy {
        ViewModelProviders.of(this,
            ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService))
        ).get(UserViewModel::class.java)
    }
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
        viewModel.getUserLiveData().observe(requireActivity(), { res ->
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
        adapter.attachCallback(object : ClickCallback{
            override fun <T> onClick(data: T?) {
                Toast.makeText(requireContext(), "message", Toast.LENGTH_LONG).show()
            }
        })
    }
}