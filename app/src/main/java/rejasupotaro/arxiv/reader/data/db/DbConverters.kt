package rejasupotaro.arxiv.reader.data.db

import android.arch.persistence.room.TypeConverter
import com.google.gson.reflect.TypeToken
import org.joda.time.DateTime
import rejasupotaro.arxiv.reader.data.gson
import java.util.*


class StringConverter {
    @TypeConverter
    fun serialize(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun deserialize(value: String): List<String> {
        return gson.fromJson(value, object : TypeToken<ArrayList<String>>() {}.type)
    }
}


class DateTimeConverter {
    @TypeConverter
    fun serialize(dateTime: DateTime): String {
        return gson.toJson(dateTime)
    }

    @TypeConverter
    fun deserialize(value: String): DateTime {
        return gson.fromJson(value, object : TypeToken<DateTime>() {}.type)
    }
}