package rejasupotaro.arxiv.reader.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import rejasupotaro.arxiv.reader.data.model.SearchHistory

@Dao
interface SearchHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searchHistory: SearchHistory)

    @Query("SELECT * FROM search_histories ORDER BY created DESC")
    fun findAll(): List<SearchHistory>

    @Query("SELECT * FROM search_histories ORDER BY created DESC LIMIT 1")
    fun first(): SearchHistory

    @Query("SELECT * FROM search_histories GROUP BY query LIMIT 10")
    fun latest(): List<SearchHistory>
}

