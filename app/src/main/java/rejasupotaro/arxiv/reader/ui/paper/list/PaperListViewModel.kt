package rejasupotaro.arxiv.reader.ui.paper.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import rejasupotaro.arxiv.reader.OpenClassOnDebug
import rejasupotaro.arxiv.reader.data.file.FileManager
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.repository.PaperRepository

@OpenClassOnDebug
class PaperListViewModel(private val context: Context, private val paperRepository: PaperRepository) : ViewModel() {
    fun paperList(): LiveData<List<Paper>> = paperRepository.list()
    fun deletePaper(paper: Paper): LiveData<Unit> {
        val file = FileManager.paperToFile(context, paper)
        return paperRepository.delete(paper to file)
    }
}
