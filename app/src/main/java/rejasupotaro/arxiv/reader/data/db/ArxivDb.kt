package rejasupotaro.arxiv.reader.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.model.SearchHistory

@Database(
        entities = arrayOf(
                Paper::class,
                SearchHistory::class
        ),
        version = 1
)
@TypeConverters(
        StringConverter::class,
        DateTimeConverter::class
)
abstract class ArxivDb : RoomDatabase() {
    abstract fun paperDao(): PaperDao
    abstract fun searchHistoryDao(): SearchHistoryDao
}
