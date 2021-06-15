package ua.kiss.topdevs.network

import ua.kiss.topdevs.models.ApiUser
import ua.kiss.topdevs.models.User

interface ApiHelper {
    //get all users
    suspend fun getAllUsers(): List<ApiUser>
}