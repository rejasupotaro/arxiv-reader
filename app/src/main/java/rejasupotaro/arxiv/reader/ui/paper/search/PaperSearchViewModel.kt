package rejasupotaro.arxiv.reader.ui.paper.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import org.joda.time.DateTime
import rejasupotaro.arxiv.reader.data.db.DbManager
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.model.SearchHistory
import rejasupotaro.arxiv.reader.data.repo.PaperRepository
import rejasupotaro.arxiv.reader.extensions.observable
import rejasupotaro.arxiv.reader.extensions.switchMap
import rejasupotaro.arxiv.reader.job.PdfDownloadServiceAutoBundle

class PaperSearchViewModel(
        val repository: PaperRepository = PaperRepository(),
        val db: DbManager = DbManager
) : ViewModel() {
    private val submitEvent = MutableLiveData<SearchRequest>()

    var query: String = ""
        set(value) {
            if (field == value) {
                return
            }
            logQuery(value)
            submitEvent.value = SearchRequest(value, 1)
            field = value
        }

    var searchResults: LiveData<SearchResponse> = submitEvent.switchMap { (query, page, perPage) ->
        observable {
            repository.search(query, page, perPage)
        }
    }

    fun loadNextPage() {
        if (query.isNullOrEmpty()) {
            return
        }

        submitEvent.value?.let {
            submitEvent.value = SearchRequest(query, it.page + 1)
        }
    }

    fun latestQueries(): LiveData<List<String>> {
        return observable {
            db.searchHistoryDao.latest().map { it.query }
        }
    }

    fun download(context: Context, paper: Paper) {
        val intent = PdfDownloadServiceAutoBundle.builder(paper)
                .build(context)
        context.startService(intent)
    }

    private fun logQuery(query: String) {
        async(CommonPool) {
            db.searchHistoryDao.insert(SearchHistory(query, DateTime.now()))
        }
    }
}

data class SearchRequest(val query: String, val page: Int, val perPage: Int = 20)
data class SearchResponse(val query: String, val result: List<Paper>, val page: Int, val totalPages: Int)
