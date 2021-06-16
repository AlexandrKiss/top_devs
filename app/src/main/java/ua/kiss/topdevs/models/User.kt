package ua.kiss.topdevs.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity
data class User(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val createdAt: Long,
    val name: String,
    val avatar: String
) {
    fun getFormatDate(): String {
        val date = Date(createdAt)
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return simpleDateFormat.format(date)
    }
}