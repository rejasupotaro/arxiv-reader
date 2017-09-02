package rejasupotaro.arxiv.reader.ui.paper.list

import android.app.Activity
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import rejasupotaro.arxiv.reader.OpenClassOnDebug
import rejasupotaro.arxiv.reader.data.file.FileManager
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.repository.PaperRepository
import rejasupotaro.arxiv.reader.extensions.switchMap

@OpenClassOnDebug
class PaperListViewModel(
        private val activity: Activity,
        private val paperRepository: PaperRepository
) : ViewModel() {
    private val loadEvent = MutableLiveData<Unit>()

    val papers = loadEvent.switchMap {
        paperRepository.all()
    }

    fun deletePaper(paper: Paper): LiveData<Unit> {
        val file = FileManager.paperToFile(activity, paper)
        return paperRepository.delete(paper to file)
    }

    fun loadPaperList() {
        loadEvent.value = Unit
    }
}
