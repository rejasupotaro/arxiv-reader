package rejasupotaro.arxiv.reader

import android.app.Application
import com.facebook.stetho.Stetho
import net.danlew.android.joda.JodaTimeAndroid
import rejasupotaro.arxiv.reader.data.db.DbManager

class ArxivReaderApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        JodaTimeAndroid.init(this)
        DbManager.init(this)
    }
}
