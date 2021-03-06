package rejasupotaro.arxiv.reader.data.db.dao

import android.arch.persistence.room.*
import rejasupotaro.arxiv.reader.data.model.Paper

@Dao
interface PaperDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(paper: Paper): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(papers: List<Paper>): List<Long>

    @Query("SELECT * FROM papers ORDER BY COALESCE(opened_at, downloaded_at) DESC")
    fun findAll(): List<Paper>

    @Delete
    fun delete(paper: Paper)

    @Query("DELETE FROM papers")
    fun deleteAll()

    @Query("SELECT * FROM papers WHERE id = :id")
    fun findById(id: Long): Paper?

    @Query("SELECT * FROM papers WHERE id IN (:ids)")
    fun findByIds(ids: List<Long>): List<Paper>

    @Query("SELECT * FROM papers WHERE title = :title")
    fun findByTitle(title: String): Paper?

    @Update
    fun update(paper: Paper)
}
