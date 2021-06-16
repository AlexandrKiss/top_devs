package ua.kiss.topdevs.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.kiss.topdevs.network.ApiHelper
import ua.kiss.topdevs.network.ApiHelperImpl
import ua.kiss.topdevs.network.ApiService
import ua.kiss.topdevs.other.Constants
import ua.kiss.topdevs.utils.DateTypeAdapter
import java.util.*
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object ApiHelperModule{

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, DateTypeAdapter())
        .create()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String, gsonBuilder: Gson): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper
}