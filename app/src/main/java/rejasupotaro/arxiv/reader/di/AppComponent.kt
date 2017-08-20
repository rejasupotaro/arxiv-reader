package rejasupotaro.arxiv.reader.di

import android.arch.persistence.room.Room
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import rejasupotaro.arxiv.reader.ArxivReaderApplication
import rejasupotaro.arxiv.reader.data.db.ArxivDb
import rejasupotaro.arxiv.reader.data.repo.PaperRepository
import rejasupotaro.arxiv.reader.data.repo.SearchHistoryRepository
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
    fun providePaperRepository(db: ArxivDb): PaperRepository {
        return PaperRepository(db)
    }

    @Provides @Singleton
    fun provideSearchHistoryRepository(db: ArxivDb): SearchHistoryRepository {
        return SearchHistoryRepository(db)
    }
}
