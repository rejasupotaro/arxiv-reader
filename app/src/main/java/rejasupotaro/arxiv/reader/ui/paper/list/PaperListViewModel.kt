package rejasupotaro.arxiv.reader.ui.paper.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import rejasupotaro.arxiv.reader.data.db.DbManager
import rejasupotaro.arxiv.reader.data.model.Paper

class PaperListViewModel(
        val db: DbManager = DbManager

) : ViewModel() {
    fun paperList(): LiveData<List<Paper>> {
        val papers = MutableLiveData<List<Paper>>()
        async(CommonPool) {
            val result = db.paperDao.findAll()
            papers.postValue(result)
        }
        return papers
    }
}

