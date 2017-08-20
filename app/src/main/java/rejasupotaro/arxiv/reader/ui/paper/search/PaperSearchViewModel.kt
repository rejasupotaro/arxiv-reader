package rejasupotaro.arxiv.reader.ui.paper.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import rejasupotaro.arxiv.reader.data.repo.PaperRepository
import rejasupotaro.arxiv.reader.data.repo.SearchHistoryRepository
import rejasupotaro.arxiv.reader.data.repo.SearchRequest
import rejasupotaro.arxiv.reader.data.repo.SearchResponse
import rejasupotaro.arxiv.reader.extensions.flatMap
import rejasupotaro.arxiv.reader.extensions.observable
import rejasupotaro.arxiv.reader.extensions.switchMap

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
        return searchHistoryRepository.latest().flatMap { it.query }
    }
}
