package rejasupotaro.arxiv.reader.ui.paper.list

import android.arch.lifecycle.ViewModel
import rejasupotaro.arxiv.reader.data.db.DbManager
import rejasupotaro.arxiv.reader.data.model.Paper

class PaperListViewModel(
        val db: DbManager = DbManager

) : ViewModel() {
    fun findAll(): List<Paper> {
        return db.paperDao.findAll()
    }
}

