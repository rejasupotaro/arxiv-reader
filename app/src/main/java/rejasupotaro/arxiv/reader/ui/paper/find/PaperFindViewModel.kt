package rejasupotaro.arxiv.reader.ui.paper.find

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import rejasupotaro.arxiv.reader.extensions.switchMap
import rejasupotaro.arxiv.reader.model.Paper
import rejasupotaro.arxiv.reader.repo.PaperRepository
import java.io.File

class PaperFindViewModel(val repository: PaperRepository = PaperRepository()) : ViewModel() {
    private val submitEvent = MutableLiveData<String>()

    var searchResults: LiveData<List<Paper>>

    var query: String = ""
        set(value) {
            if (field == value) {
                return
            }
            field = value
            submitEvent.value = field
        }

    init {
        searchResults = submitEvent.switchMap { query ->
            repository.search(query)
        }
    }

    fun download(context: Context, paper: Paper) {
        val file = File(context.filesDir, paper.title)
        repository.download(paper, file)
    }
}
