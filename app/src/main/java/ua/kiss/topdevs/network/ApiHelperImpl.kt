package ua.kiss.topdevs.network

import ua.kiss.topdevs.models.ApiUser
import ua.kiss.topdevs.models.User

class ApiHelperImpl (private val apiService: ApiService) : ApiHelper {
    override suspend fun getAllUsers(): List<ApiUser> = apiService.getAllUsers()
}