package rejasupotaro.arxiv.reader.ui.paper.list

import android.app.Activity
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import rejasupotaro.arxiv.reader.data.repository.PaperRepository

@Module
class PaperListActivityModule {
    @Provides
    fun provideActivity(activity: PaperListActivity): Activity {
        return activity
    }

    @Provides
    fun providePaperListViewModel(activity: Activity, paperRepository: PaperRepository): PaperListViewModel {
        return PaperListViewModel(activity, paperRepository)
    }
}
