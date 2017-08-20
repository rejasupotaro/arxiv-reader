package rejasupotaro.arxiv.reader.ui.paper.read

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import org.joda.time.DateTime
import rejasupotaro.arxiv.reader.data.db.ArxivDb
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.repo.PaperRepository
import rejasupotaro.arxiv.reader.extensions.map
import rejasupotaro.arxiv.reader.extensions.observable
import rejasupotaro.arxiv.reader.extensions.switchMap

class PaperReadViewModel(private val paperRepository: PaperRepository) : ViewModel() {
    var paperId: MutableLiveData<Long> = MutableLiveData()

    var paper: LiveData<Paper> = paperId.switchMap { paperId ->
        paperRepository.openById(paperId)
    }

    fun updatePaperLastOpenedPage(page: Int, totalPage: Int): LiveData<Unit> {
        return paper.value?.let {
            it.lastOpenedPage = page + 1
            it.totalPage = totalPage
            paperRepository.update(it).map { Unit }
        } ?: observable { Unit }
    }
}

