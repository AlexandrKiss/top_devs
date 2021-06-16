package ua.kiss.topdevs.database.dao

import androidx.room.*
import ua.kiss.topdevs.models.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAll(): List<User>

    @Query("SELECT * FROM User WHERE id = :id")
    fun getUser(id: Long): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM User")
    fun deleteAll()
}