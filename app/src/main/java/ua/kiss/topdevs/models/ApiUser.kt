package ua.kiss.topdevs.models

import java.text.SimpleDateFormat
import java.util.*

data class ApiUser(
    val id: Long,
    val createdAt: Date,
    val name: String,
    val avatar: String
) {
    fun getFormatDate(): String {
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return simpleDateFormat.format(this.createdAt)
    }
}