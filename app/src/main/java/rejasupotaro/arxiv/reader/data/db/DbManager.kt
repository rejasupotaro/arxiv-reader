package rejasupotaro.arxiv.reader.data.db

import android.arch.persistence.room.Room
import android.content.Context

object DbManager {

    private lateinit var db: ArxivDb

    val paperDao by lazy {
        db.paperDao()
    }

    fun init(context: Context) {
        db = Room.databaseBuilder(context, ArxivDb::class.java, "arxiv")
                .build()
    }
}

