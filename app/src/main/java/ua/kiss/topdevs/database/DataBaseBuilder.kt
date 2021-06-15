package ua.kiss.topdevs.database

import android.content.Context
import androidx.room.Room

object DataBaseBuilder {
    private var INSTANCE: AppDataBase? = null

    fun getInstance(context: Context): AppDataBase {
        if (INSTANCE == null) {
            synchronized(AppDataBase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            AppDataBase::class.java,
            "top_devs")
            .allowMainThreadQueries()
            .build()
}