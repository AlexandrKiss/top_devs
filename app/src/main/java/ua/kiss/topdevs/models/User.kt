package ua.kiss.topdevs.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val createdAt: Long,
    val name: String,
    val avatar: String
)
