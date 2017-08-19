package rejasupotaro.arxiv.reader

import android.app.Activity
import android.app.Application
import android.app.Service
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import net.danlew.android.joda.JodaTimeAndroid
import rejasupotaro.arxiv.reader.di.AppComponent
import rejasupotaro.arxiv.reader.di.DaggerAppComponent
import javax.inject.Inject

open class ArxivReaderApplication : Application(), HasActivityInjector, HasServiceInjector {
    @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var serviceDispatchingAndroidInjector: DispatchingAndroidInjector<Service>

    override fun onCreate() {
        super.onCreate()
        setup()
    }

    private fun setup() {
        injector().inject(this)
        Stetho.initializeWithDefaults(this)
        JodaTimeAndroid.init(this)
    }

    open fun injector(): AppComponent {
        return DaggerAppComponent.builder()
                .application(this)
                .build()
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return serviceDispatchingAndroidInjector
    }
}
