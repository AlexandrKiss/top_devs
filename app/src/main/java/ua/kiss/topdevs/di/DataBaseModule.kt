package ua.kiss.topdevs.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ua.kiss.topdevs.database.AppDataBase
import ua.kiss.topdevs.database.dao.UserDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataBaseModule {
    @Provides
    fun provideChannelDao(appDatabase: AppDataBase): UserDao =
        appDatabase.getUserDao()

    @Provides
    @Singleton
    fun provideAppDatabase (@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder (
            appContext,
            AppDataBase::class.java ,
            "top_devs"
        ).allowMainThreadQueries().build ()
    }
}