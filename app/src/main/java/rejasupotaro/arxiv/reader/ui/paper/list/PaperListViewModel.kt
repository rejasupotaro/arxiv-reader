package rejasupotaro.arxiv.reader.ui.paper.list

import android.app.Activity
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import rejasupotaro.arxiv.reader.OpenClassOnDebug
import rejasupotaro.arxiv.reader.data.file.FileManager
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.repository.PaperRepository

@OpenClassOnDebug
class PaperListViewModel(private val activity: Activity, private val paperRepository: PaperRepository) : ViewModel() {
    fun paperList(): LiveData<List<Paper>> = paperRepository.list()
    fun deletePaper(paper: Paper): LiveData<Unit> {
        val file = FileManager.paperToFile(activity, paper)
        return paperRepository.delete(paper to file)
    }
}
