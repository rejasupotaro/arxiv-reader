package rejasupotaro.arxiv.reader.ui.paper.read

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import org.joda.time.DateTime
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.repository.PaperRepository
import rejasupotaro.arxiv.reader.extensions.map
import rejasupotaro.arxiv.reader.extensions.observable
import rejasupotaro.arxiv.reader.extensions.switchMap

class PaperReadViewModel(private val paperRepository: PaperRepository) : ViewModel() {
    var paperId: MutableLiveData<Long> = MutableLiveData()

    var paper: LiveData<Paper?> = paperId
            .switchMap { paperId ->
                openPaperById(paperId)
            }

    fun updatePaperLastOpenedPage(page: Int, totalPage: Int): LiveData<Unit> {
        return paper.value?.let { paper ->
            paper.lastOpenedPage = page
            paper.totalPage = totalPage
            paperRepository.update(paper).map { Unit }
        } ?: observable { Unit }
    }

    private fun openPaperById(paperId: Long): LiveData<Paper?> {
        return paperRepository.findById(paperId)
                .map { paper ->
                    paper?.apply {
                        paper.openedAt = DateTime.now()
                        paperRepository.update(paper)
                    }
                    paper
                }
    }
}

