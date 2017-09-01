package rejasupotaro.arxiv.reader.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import rejasupotaro.arxiv.reader.data.model.PaperSimilarity

@Dao
interface PaperSimilarityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(paperSimilarity: PaperSimilarity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(paperSimilarities: List<PaperSimilarity>): List<Long>

    @Query("DELETE FROM paper_similarities")
    fun deleteAll()

    @Query("SELECT * FROM paper_similarities WHERE from_paper_id = :from_paper_id ORDER BY similarity DESC LIMIT :n")
    fun findByFromPaperId(from_paper_id: Long, n: Int = 10): List<PaperSimilarity>
}
