package rejasupotaro.arxiv.reader.ui.my_paper.list

import android.arch.lifecycle.ViewModel
import rejasupotaro.arxiv.reader.db.DbManager
import rejasupotaro.arxiv.reader.model.Paper

class MyPaperListViewModel(
        val db: DbManager = DbManager

) : ViewModel() {
    fun findAll(): List<Paper> {
        return db.paperDao.findAll()
    }
}

