package rejasupotaro.arxiv.reader.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import rejasupotaro.arxiv.reader.model.Author

@Database(
        entities = arrayOf(Author::class),
        version = 1
)
abstract class ArxivDb : RoomDatabase() {
    abstract fun authorDao(): AuthorDao
}
