package ua.kiss.topdevs.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

internal class DateTypeAdapter : JsonDeserializer<Date?> {
    private val dateFormat = "yyyy-MM-dd HH:mm" //2021-04-08 23:00
    private val dateFormatter = SimpleDateFormat(dateFormat, Locale.getDefault())

    @Throws(JsonParseException::class)
    override fun deserialize(
            json: JsonElement, typeOfT: Type?,
            context: JsonDeserializationContext?
    ): Date? {
        return dateFormatter.parse(json.asString)
    }
}