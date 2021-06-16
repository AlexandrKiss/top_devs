package ua.kiss.topdevs.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.kiss.topdevs.database.dao.UserDao
import ua.kiss.topdevs.models.ApiUser
import ua.kiss.topdevs.models.User
import ua.kiss.topdevs.network.ApiHelper
import ua.kiss.topdevs.other.Resource
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject constructor(private val apiHelper: ApiHelper, private val userDao: UserDao): ViewModel() {
    private val userListLiveData = MutableLiveData<Resource<List<ApiUser>>>()
    fun getUserListLiveData(): LiveData<Resource<List<ApiUser>>> = userListLiveData

    private val userLiveData = MutableLiveData<Resource<User>>()
    fun getUserLiveData(): LiveData<Resource<User>> = userLiveData



    init {
        loadUser()
    }

    private fun loadUser() {
        userListLiveData.postValue(Resource.loading(null))
        viewModelScope.launch {
            try {
                userListLiveData.postValue(Resource.success(apiHelper.getAllUsers()))
            } catch (e: Exception) {
                e.stackTrace
                userListLiveData.postValue(e.message?.let { Resource.error(it, null) })
            }
        }
    }

    fun saveUser(apiUser: ApiUser) {
        viewModelScope.launch {
            userDao.addUser(apiUser.getUser())
        }
    }

    fun getUser(id: Long) {
        userLiveData.postValue(Resource.loading(null))
        viewModelScope.launch {
            try {
                userLiveData.postValue(Resource.success(userDao.getUser(id)))
            } catch (e: Exception) {
                userLiveData.postValue(e.message?.let { Resource.error(it, null) })
            }
        }
    }
}