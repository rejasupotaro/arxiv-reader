package rejasupotaro.arxiv.reader.di

import android.arch.persistence.room.Room
import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import okhttp3.OkHttpClient
import rejasupotaro.arxiv.reader.ArxivReaderApplication
import rejasupotaro.arxiv.reader.data.db.ArxivDb
import rejasupotaro.arxiv.reader.data.http.HttpClient
import rejasupotaro.arxiv.reader.data.repository.PaperRepository
import rejasupotaro.arxiv.reader.data.repository.SearchHistoryRepository
import javax.inject.Singleton

@Component(
        modules = arrayOf(
                AndroidInjectionModule::class,
                AppModule::class,
                BindingModule::class
        )
)
@Singleton interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: ArxivReaderApplication): Builder
        fun build(): AppComponent
    }

    fun inject(application: ArxivReaderApplication)
}

@Module
class AppModule {
    @Provides @Singleton
    fun provideContext(application: ArxivReaderApplication): Context {
        return application.applicationContext
    }

    @Provides @Singleton
    fun provideArxivDb(context: Context): ArxivDb {
        return Room.databaseBuilder(context, ArxivDb::class.java, "arxiv").build()
    }

    @Provides @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()
    }

    @Provides @Singleton
    fun provideHttpClient(okHttpClient: OkHttpClient): HttpClient {
        return HttpClient(okHttpClient)
    }

    @Provides @Singleton
    fun providePaperRepository(db: ArxivDb, httpClient: HttpClient): PaperRepository {
        return PaperRepository(db, httpClient)
    }

    @Provides @Singleton
    fun provideSearchHistoryRepository(db: ArxivDb): SearchHistoryRepository {
        return SearchHistoryRepository(db)
    }
}
