package rejasupotaro.arxiv.reader.job

import android.app.IntentService
import android.content.Intent
import com.yatatsu.autobundle.AutoBundleField
import rejasupotaro.arxiv.reader.data.db.DbManager
import rejasupotaro.arxiv.reader.data.file.FileManager
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.model.PaperConverter
import rejasupotaro.arxiv.reader.data.repo.PaperRepository

class PdfDownloadService(
        name: String = PdfDownloadService::class.java.simpleName,
        val repository: PaperRepository = PaperRepository(),
        val db: DbManager = DbManager
) : IntentService(name) {
    @AutoBundleField(converter = PaperConverter::class)
    lateinit var paper: Paper

    override fun onHandleIntent(intent: Intent) {
        PdfDownloadServiceAutoBundle.bind(this, intent)
        download(paper)
    }

    fun download(paper: Paper) {
        val file = FileManager.paperToFile(applicationContext, paper)
        repository.download(paper, file)
        db.paperDao.insert(paper)
    }
}
