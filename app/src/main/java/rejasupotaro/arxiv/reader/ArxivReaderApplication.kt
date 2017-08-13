package rejasupotaro.arxiv.reader

import android.app.Application
import com.facebook.stetho.Stetho
import rejasupotaro.arxiv.reader.data.db.DbManager

class ArxivReaderApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        DbManager.init(this)
    }
}
