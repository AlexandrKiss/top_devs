package ua.kiss.topdevs.network

import retrofit2.http.GET
import ua.kiss.topdevs.models.ApiUser

interface ApiService {
    @GET("/users")
    suspend fun getAllUsers(): List<ApiUser>
}