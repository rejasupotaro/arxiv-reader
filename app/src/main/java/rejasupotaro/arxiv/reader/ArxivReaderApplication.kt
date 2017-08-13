package rejasupotaro.arxiv.reader

import android.app.Application
import rejasupotaro.arxiv.reader.db.DbManager

class ArxivReaderApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DbManager.init(this)
    }
}
