package rejasupotaro.arxiv.reader.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import rejasupotaro.arxiv.reader.data.db.dao.PaperDao
import rejasupotaro.arxiv.reader.data.db.dao.SearchHistoryDao
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.model.PaperSimilarity
import rejasupotaro.arxiv.reader.data.model.SearchHistory

@Database(
        entities = arrayOf(
                Paper::class,
                SearchHistory::class,
                PaperSimilarity::class
        ),
        version = 2
)
@TypeConverters(
        StringListConverter::class,
        DateTimeConverter::class,
        CategoryConverter::class
)
abstract class ArxivDb : RoomDatabase() {
    abstract fun paperDao(): PaperDao
    abstract fun searchHistoryDao(): SearchHistoryDao
}
