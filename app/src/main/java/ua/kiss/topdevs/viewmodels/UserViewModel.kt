package ua.kiss.topdevs.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.kiss.topdevs.models.ApiUser
import ua.kiss.topdevs.network.ApiHelper
import ua.kiss.topdevs.utils.Resource

class UserViewModel(private val apiHelper: ApiHelper): ViewModel() {
    private val userLiveData = MutableLiveData<Resource<List<ApiUser>>>()

    fun getUserLiveData(): LiveData<Resource<List<ApiUser>>> = userLiveData

    init {
        loadUser()
    }

    private fun loadUser() {
        userLiveData.postValue(Resource.loading(null))
        viewModelScope.launch {
            try {
                userLiveData.postValue(Resource.success(apiHelper.getAllUsers()))
            } catch (e: Exception) {
                e.stackTrace
                userLiveData.postValue(e.message?.let { Resource.error(it, null) })
            }
        }
    }
}