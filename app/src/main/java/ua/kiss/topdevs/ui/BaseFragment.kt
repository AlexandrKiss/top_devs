package ua.kiss.topdevs.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import ua.kiss.topdevs.database.DataBaseBuilder
import ua.kiss.topdevs.network.ApiHelperImpl
import ua.kiss.topdevs.network.RetrofitBuilder
import ua.kiss.topdevs.utils.ViewModelFactory
import ua.kiss.topdevs.viewmodels.UserViewModel

open class BaseFragment : Fragment() {
    val viewModel by lazy {
        ViewModelProviders.of(this,
                ViewModelFactory(
                        ApiHelperImpl(RetrofitBuilder.apiService),
                        DataBaseBuilder.getInstance(requireContext())
                )
        ).get(UserViewModel::class.java)
    }
}