package rejasupotaro.arxiv.reader.ui.paper.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import rejasupotaro.arxiv.reader.data.db.DbManager
import rejasupotaro.arxiv.reader.data.file.FileManager
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.model.SearchHistory
import rejasupotaro.arxiv.reader.data.repo.PaperRepository
import rejasupotaro.arxiv.reader.extensions.deferredMap
import rejasupotaro.arxiv.reader.extensions.observable
import rejasupotaro.arxiv.reader.extensions.switchMap
import java.util.*

class PaperSearchViewModel(
        val repository: PaperRepository = PaperRepository(),
        val db: DbManager = DbManager
) : ViewModel() {
    private val submitEvent = MutableLiveData<String>()
    var query: String = ""
        set(value) {
            if (field == value) {
                return
            }
            field = value

            logQuery(field)
            submitEvent.value = field
        }
    var searchResults: LiveData<List<Paper>> = submitEvent.switchMap { query ->
        repository.search(query)
    }

    fun latestQueries(): LiveData<List<String>> {
        return observable {
            db.searchHistoryDao.latest().map { it.query }
        }
    }

    fun download(context: Context, paper: Paper): LiveData<Unit> {
        val file = FileManager.paperToFile(context, paper)
        return repository.download(paper, file).deferredMap {
            db.paperDao.insert(paper)
        }
    }

    private fun logQuery(query: String) {
        async(CommonPool) {
            db.searchHistoryDao.insert(SearchHistory(query, Date()))
        }
    }
}
