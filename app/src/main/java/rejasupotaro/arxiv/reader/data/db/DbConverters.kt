package rejasupotaro.arxiv.reader.data.db

import android.arch.persistence.room.TypeConverter
import com.google.gson.reflect.TypeToken
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


class DateConverter {
    @TypeConverter
    fun serialize(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun deserialize(value: Long): Date {
        return Date(value)
    }
}