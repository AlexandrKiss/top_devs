package ua.kiss.topdevs.network

import ua.kiss.topdevs.models.ApiUser

interface ApiHelper {
    //get all users
    suspend fun getAllUsers(): List<ApiUser>
}