package ua.kiss.topdevs.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.kiss.topdevs.utils.DateTypeAdapter
import java.util.*

object RetrofitBuilder {
    private const val BASE_URL = "https://60c8cb9d3fcd810017036d0b.mockapi.io/api/"

    private fun getRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder()

        val gsonBuilder = GsonBuilder()
            .registerTypeAdapter(Date::class.java, DateTypeAdapter())
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}