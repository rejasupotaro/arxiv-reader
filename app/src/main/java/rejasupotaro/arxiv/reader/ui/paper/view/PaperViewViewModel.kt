package rejasupotaro.arxiv.reader.ui.paper.view

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import org.joda.time.DateTime
import rejasupotaro.arxiv.reader.data.db.DbManager
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.extensions.map
import rejasupotaro.arxiv.reader.extensions.observable
import rejasupotaro.arxiv.reader.extensions.switchMap

class PaperViewViewModel(
        private val paperId: Long,
        private val db: DbManager = DbManager
) : ViewModel() {
    val paper: LiveData<Paper> = observable {
        val paper = db.paperDao.findById(paperId)
        paper.openedAt = DateTime.now()
        paper
    }.switchMap { paper ->
        updatePaper(paper)
    }

    fun updatePaper(paper: Paper): LiveData<Paper> {
        return observable {
            db.paperDao.update(paper)
            paper
        }
    }

    fun updatePaperLastOpenedPage(page: Int): LiveData<Unit> {
        return paper.value?.let {
            it.lastOpenedPage = page
            updatePaper(it).map { Unit }
        } ?: observable { Unit }
    }
}

