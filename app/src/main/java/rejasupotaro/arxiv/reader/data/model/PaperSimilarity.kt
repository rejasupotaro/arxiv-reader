package rejasupotaro.arxiv.reader.data.model

import android.arch.persistence.room.*

@Entity(
        tableName = "paper_similarities",
        indices = arrayOf(
                Index(value = *arrayOf("from_paper_id", "to_paper_id"), unique = true)
        ),
        foreignKeys = arrayOf(
                ForeignKey(
                        entity = Paper::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("from_paper_id"),
                        onDelete = ForeignKey.CASCADE
                ),
                ForeignKey(
                        entity = Paper::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("to_paper_id"),
                        onDelete = ForeignKey.CASCADE
                )
        )
)
data class PaperSimilarity(
        @ColumnInfo(name = "from_paper_id")
        val fromPaperId: Long,

        @ColumnInfo(name = "to_paper_id")
        val toPaperId: Long,

        @ColumnInfo(name = "similarity")
        var similarity: Double
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0

}

