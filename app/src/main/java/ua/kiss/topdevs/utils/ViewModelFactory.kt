package ua.kiss.topdevs.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.kiss.topdevs.network.ApiHelper
import ua.kiss.topdevs.viewmodels.UserViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(apiHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}