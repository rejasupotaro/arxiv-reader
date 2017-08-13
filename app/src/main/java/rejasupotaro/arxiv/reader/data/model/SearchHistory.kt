package rejasupotaro.arxiv.reader.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(
        tableName = "search_histories"
)
data class SearchHistory(
        @ColumnInfo(name = "query")
        var query: String,

        @ColumnInfo(name = "created")
        var created: Date
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}
