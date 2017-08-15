package rejasupotaro.arxiv.reader.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.joda.time.DateTime
import java.util.*

@Entity(
        tableName = "search_histories"
)
data class SearchHistory(
        @ColumnInfo(name = "query")
        var query: String,

        @ColumnInfo(name = "created_at")
        var createdAt: DateTime
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}
