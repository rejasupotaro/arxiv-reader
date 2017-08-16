package rejasupotaro.arxiv.reader.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import org.joda.time.DateTime
import rejasupotaro.arxiv.reader.data.api.PaperEntity

@Entity(
        tableName = "papers",
        indices = arrayOf(
                Index(value = "title", unique = true)
        )
)
data class Paper(
        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "summary")
        var summary: String,

        @ColumnInfo(name = "authors")
        var authors: List<String>,

        // This value can take below values
        // "Statistics - *", "Quantitative Biology - *", "Computer Science - *",
        // "Nonlinear Sciences - *", "Mathematics - *", "Astrophysics",
        // "Physics", "High Energy Physics", "Mathematical Physics",
        // "Nuclear Experiment", "Nuclear Theory", "Physics - *", "Quantum Physics"
        @ColumnInfo(name = "categories")
        var categories: List<String>,

        @ColumnInfo(name = "download_url")
        var downloadUrl: String,

        @ColumnInfo(name = "published_at")
        var publishedAt: DateTime,

        @ColumnInfo(name = "updated_at")
        var updatedAt: DateTime,

        @ColumnInfo(name = "opened_at")
        var openedAt: DateTime,

        @ColumnInfo(name = "last_opened_page")
        var lastOpenedPage: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0

    companion object {
        fun entityToModel(entity: PaperEntity): Paper {
            return Paper(
                    title = entity.title.trim(),
                    summary = entity.summary.trim(),
                    authors = entity.authors.map { it.name },
                    categories = entity.categories.map { it.description }.filter { it.isNotEmpty() },
                    downloadUrl = entity.links.filter { it.title == "pdf" }.first().href,
                    publishedAt = entity.published,
                    updatedAt = entity.updated,
                    openedAt = DateTime.now(),
                    lastOpenedPage = 0
            )
        }
    }
}
