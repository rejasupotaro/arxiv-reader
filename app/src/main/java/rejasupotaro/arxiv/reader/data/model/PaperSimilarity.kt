package rejasupotaro.arxiv.reader.data.model

import android.arch.persistence.room.*

@Entity(
        tableName = "paper_similarities",
        indices = arrayOf(
                Index(value = "from_paper_id", unique = true),
                Index(value = "to_paper_id", unique = true)
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
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Long,

        @ColumnInfo(name = "from_paper_id")
        val fromPaperId: Long,

        @ColumnInfo(name = "to_paper_id")
        val toPaperId: Long,

        @ColumnInfo(name = "similarity")
        var similarity: Double
)

