package rejasupotaro.arxiv.reader.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import rejasupotaro.arxiv.reader.data.model.Paper

@Dao
interface PaperDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(paper: Paper)

    @Query("SELECT * FROM papers")
    fun findAll(): List<Paper>
}
