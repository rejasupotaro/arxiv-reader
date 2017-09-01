package rejasupotaro.arxiv.reader.ui.paper.view

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.repository.PaperRepository
import rejasupotaro.arxiv.reader.extensions.observable
import rejasupotaro.arxiv.reader.extensions.switchMap

class PaperViewViewModel(
        private val paperRepository: PaperRepository
) : ViewModel() {
    fun loadPaper(title: String): LiveData<Paper?> {
        return paperRepository.findByTitle(title)
    }

    fun loadSimilarPapers(title: String): LiveData<List<Pair<Paper, Double>>> {
        return loadPaper(title).switchMap { paper ->
            paper?.let {
                paperRepository.similarPapers(paper.id)
            } ?: observable {
                listOf<Pair<Paper, Double>>()
            }
        }
    }
}

