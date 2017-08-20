package rejasupotaro.arxiv.reader.ui.paper.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import rejasupotaro.arxiv.reader.OpenClassOnDebug
import rejasupotaro.arxiv.reader.data.db.ArxivDb
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.repo.PaperRepository
import rejasupotaro.arxiv.reader.extensions.observable

@OpenClassOnDebug
class PaperListViewModel(private val paperRepository: PaperRepository) : ViewModel() {
    fun paperList(): LiveData<List<Paper>> = paperRepository.list()
    fun deletePaper(paper: Paper): LiveData<Unit> = paperRepository.delete(paper)
}
