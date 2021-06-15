package ua.kiss.topdevs.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.kiss.topdevs.database.dao.UserDao
import ua.kiss.topdevs.models.User

@Database(entities = [User::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
}