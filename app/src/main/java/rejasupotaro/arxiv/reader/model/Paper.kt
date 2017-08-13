package rejasupotaro.arxiv.reader.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import rejasupotaro.arxiv.reader.api.PaperEntity

@Entity(tableName = "papers")
data class Paper(
        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "summary")
        var summary: String,

        @ColumnInfo(name = "authors")
        var authors: List<String>,

        @ColumnInfo(name = "download_url")
        var downloadUrl: String
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
                    downloadUrl = entity.links.filter { it.title == "pdf" }.first().href
            )
        }
    }
}
