package rejasupotaro.arxiv.reader.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "authors")
data class Author(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Long,

        @SerializedName("name")
        var name: String
)
