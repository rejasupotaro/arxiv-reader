package rejasupotaro.arxiv.reader.data.db

import android.arch.persistence.room.TypeConverter
import com.google.gson.reflect.TypeToken
import rejasupotaro.arxiv.reader.data.gson

class StringConverter {
    @TypeConverter
    fun fromString(value: String): List<String> {
        return gson.fromJson(value, object : TypeToken<ArrayList<String>>() {}.type)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return gson.toJson(list)
    }
}
