package rejasupotaro.arxiv.reader.ui.paper.find

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import rejasupotaro.arxiv.reader.model.Paper
import rejasupotaro.arxiv.reader.repo.PaperRepository

class PaperFindViewModel(val repository: PaperRepository = PaperRepository()) : ViewModel() {
    lateinit var searchResults: LiveData<List<Paper>>

    init {
        searchResults = repository.search()
    }
}

