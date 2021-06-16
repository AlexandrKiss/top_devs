package ua.kiss.topdevs.network

import ua.kiss.topdevs.models.ApiUser
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getAllUsers(): List<ApiUser> = apiService.getAllUsers()
}