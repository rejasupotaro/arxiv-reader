package rejasupotaro.arxiv.reader.data.repository

import android.arch.lifecycle.LiveData
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import org.joda.time.DateTime
import rejasupotaro.arxiv.reader.data.db.ArxivDb
import rejasupotaro.arxiv.reader.data.model.SearchHistory
import rejasupotaro.arxiv.reader.extensions.observable

class SearchHistoryRepository(private val db: ArxivDb) {
    fun latest(): LiveData<List<SearchHistory>> {
        return observable {
            db.searchHistoryDao().latest()
        }
    }

    fun log(query: String) {
        async(CommonPool) {
            db.searchHistoryDao().insert(SearchHistory(query, DateTime.now()))
        }
    }
}

