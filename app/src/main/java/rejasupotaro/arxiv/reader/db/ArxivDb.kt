package rejasupotaro.arxiv.reader.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import rejasupotaro.arxiv.reader.model.Paper

@Database(
        entities = arrayOf(Paper::class),
        version = 1
)
@TypeConverters(StringConverter::class)
abstract class ArxivDb : RoomDatabase() {
    abstract fun paperDao(): PaperDao
}
