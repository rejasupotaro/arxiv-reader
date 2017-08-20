package rejasupotaro.arxiv.reader.ui.paper.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.repo.PaperRepository
import rejasupotaro.arxiv.reader.data.repo.SearchHistoryRepository
import rejasupotaro.arxiv.reader.extensions.map
import rejasupotaro.arxiv.reader.extensions.observable
import rejasupotaro.arxiv.reader.extensions.switchMap
import rejasupotaro.arxiv.reader.job.PdfDownloadServiceAutoBundle

class PaperSearchViewModel(
        private val paperRepository: PaperRepository,
        private val searchHistoryRepository: SearchHistoryRepository
) : ViewModel() {
    private val submitEvent = MutableLiveData<SearchRequest>()

    var query: String = ""
        set(value) {
            if (field == value) {
                return
            }
            searchHistoryRepository.log(value)
            submitEvent.value = SearchRequest(value, 1)
            field = value
        }

    var searchResults: LiveData<SearchResponse> = submitEvent.switchMap { (query, page, perPage) ->
        observable {
            paperRepository.search(query, page, perPage)
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
        return searchHistoryRepository.latest().map { it.map { it.query } }
    }

    fun download(context: Context, paper: Paper) {
        val intent = PdfDownloadServiceAutoBundle.builder(paper)
                .build(context)
        context.startService(intent)
    }
}

data class SearchRequest(val query: String, val page: Int, val perPage: Int = 20)
data class SearchResponse(val query: String, val result: List<Paper>, val page: Int, val totalPages: Int)
