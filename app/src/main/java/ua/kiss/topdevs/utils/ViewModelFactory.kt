package ua.kiss.topdevs.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.kiss.topdevs.database.AppDataBase
import ua.kiss.topdevs.network.ApiHelper
import ua.kiss.topdevs.viewmodels.UserViewModel

class ViewModelFactory(private val apiHelper: ApiHelper, private val dataBase: AppDataBase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(apiHelper, dataBase) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}