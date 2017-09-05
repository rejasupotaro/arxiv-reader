package rejasupotaro.arxiv.reader.data.db

import android.arch.persistence.room.TypeConverter
import com.google.gson.reflect.TypeToken
import org.joda.time.DateTime
import rejasupotaro.arxiv.reader.data.gson
import rejasupotaro.arxiv.reader.data.model.Category
import java.util.*

class StringListConverter {
    @TypeConverter fun serialize(list: List<String>): String = gson.toJson(list)
    @TypeConverter fun deserialize(value: String): List<String> = gson.fromJson(value, object : TypeToken<List<String>>() {}.type)
}

class DateTimeConverter {
    @TypeConverter fun serialize(dateTime: DateTime?): String? = dateTime?.let { gson.toJson(it) }
    @TypeConverter fun deserialize(value: String?): DateTime? = value?.let { gson.fromJson(it, object : TypeToken<DateTime>() {}.type) }
}

class CategoryConverter {
    @TypeConverter fun serialize(categories: List<Category>): String = gson.toJson(categories)
    @TypeConverter fun deserialize(value: String): List<Category> = gson.fromJson(value, object : TypeToken<List<Category>>() {}.type)
}
