package rejasupotaro.arxiv.reader.ui.paper.find

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import rejasupotaro.arxiv.reader.data.db.DbManager
import rejasupotaro.arxiv.reader.data.file.FileManager
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.repo.PaperRepository
import rejasupotaro.arxiv.reader.extensions.map
import rejasupotaro.arxiv.reader.extensions.switchMap

class PaperFindViewModel(
        val repository: PaperRepository = PaperRepository(),
        val db: DbManager = DbManager
) : ViewModel() {
    private val submitEvent = MutableLiveData<String>()
    var query: String = ""
        set(value) {
            if (field == value) {
                return
            }
            field = value
            submitEvent.value = field
        }
    var searchResults: LiveData<List<Paper>> = submitEvent.switchMap { query ->
        repository.search(query)
    }

    fun download(context: Context, paper: Paper): LiveData<Unit> {
        val file = FileManager.paperToFile(context, paper)
        return repository.download(paper, file).map {
            db.paperDao.insert(paper)
        }
    }
}
