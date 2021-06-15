package ua.kiss.topdevs.models

import java.util.*

data class ApiUser(
    val id: Long,
    val createdAt: Date,
    val name: String,
    val avatar: String
) {
    fun getUser() =
        User(
            id = this.id,
            createdAt = this.createdAt.time,
            name = this.name,
            avatar = this.avatar
        )
}