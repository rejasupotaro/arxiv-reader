package rejasupotaro.arxiv.reader.ui.my_paper.list

import android.arch.lifecycle.ViewModel
import rejasupotaro.arxiv.reader.data.db.DbManager
import rejasupotaro.arxiv.reader.data.model.Paper

class MyPaperListViewModel(
        val db: DbManager = DbManager

) : ViewModel() {
    fun findAll(): List<Paper> {
        return db.paperDao.findAll()
    }
}

