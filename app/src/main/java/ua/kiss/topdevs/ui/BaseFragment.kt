package ua.kiss.topdevs.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ua.kiss.topdevs.viewmodels.UserViewModel

@AndroidEntryPoint
open class BaseFragment : Fragment() {
    val viewModel: UserViewModel by viewModels()
}