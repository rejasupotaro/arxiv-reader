package rejasupotaro.arxiv.reader.data.db

import android.arch.persistence.room.*
import rejasupotaro.arxiv.reader.data.model.Paper

@Dao
interface PaperDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(paper: Paper)

    @Query("SELECT * FROM papers ORDER BY opened_at DESC")
    fun findAll(): List<Paper>

    @Delete
    fun delete(paper: Paper)

    @Query("SELECT * FROM papers WHERE id = :id")
    fun findById(id: Long): Paper

    @Update
    fun update(paper: Paper)
}
