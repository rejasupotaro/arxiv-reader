package rejasupotaro.arxiv.reader.job

import android.app.IntentService
import android.content.Intent
import com.yatatsu.autobundle.AutoBundleField
import dagger.android.AndroidInjection
import rejasupotaro.arxiv.reader.data.db.ArxivDb
import rejasupotaro.arxiv.reader.data.file.FileManager
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.model.PaperConverter
import rejasupotaro.arxiv.reader.data.repository.PaperRepository
import rejasupotaro.arxiv.reader.notification.PdfDownloadedPushNotification
import rejasupotaro.arxiv.reader.notification.PdfDownloadingPushNotification
import javax.inject.Inject

class PdfDownloadService(name: String = PdfDownloadService::class.java.simpleName) : IntentService(name) {
    @Inject lateinit var repository: PaperRepository
    @Inject lateinit var db: ArxivDb

    @AutoBundleField(converter = PaperConverter::class)
    lateinit var paper: Paper

    override fun onHandleIntent(intent: Intent) {
        AndroidInjection.inject(this)
        PdfDownloadServiceAutoBundle.bind(this, intent)
        download(paper)
    }

    fun download(paper: Paper) {
        val notification = PdfDownloadingPushNotification(applicationContext, paper)
        try {
            notification.show()
            val file = FileManager.paperToFile(applicationContext, paper)
            repository.download(paper, file)
            paper.id = db.paperDao().insert(paper)
            PdfDownloadedPushNotification(applicationContext, paper).show()
        } finally {
            notification.cancel()
        }
    }
}
