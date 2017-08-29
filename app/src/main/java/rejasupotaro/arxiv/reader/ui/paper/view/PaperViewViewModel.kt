package rejasupotaro.arxiv.reader.ui.paper.view

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.repository.PaperRepository

class PaperViewViewModel(
        private val paperRepository: PaperRepository
) : ViewModel() {
    fun loadPaper(title: String): LiveData<Paper?> {
        return paperRepository.findByTitle(title)
    }
}

