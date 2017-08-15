package rejasupotaro.arxiv.reader.ui.paper.view

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import org.joda.time.DateTime
import rejasupotaro.arxiv.reader.data.db.DbManager
import rejasupotaro.arxiv.reader.data.file.FileManager
import rejasupotaro.arxiv.reader.extensions.observable
import java.io.File

class PaperViewViewModel(
        private val context: Context,
        private val paperId: Long,
        private val db: DbManager = DbManager
) : ViewModel() {
    fun loadPaper(): LiveData<File> {
        return observable {
            val paper = db.paperDao.findById(paperId)
            paper.updatedAt = DateTime.now()
            db.paperDao.update(paper)
            FileManager.paperToFile(context, paper)
        }
    }
}

