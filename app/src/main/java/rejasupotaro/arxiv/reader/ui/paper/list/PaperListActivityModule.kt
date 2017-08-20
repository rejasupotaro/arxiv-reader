package rejasupotaro.arxiv.reader.ui.paper.list

import android.content.Context
import dagger.Module
import dagger.Provides
import rejasupotaro.arxiv.reader.data.repository.PaperRepository

@Module
class PaperListActivityModule {
    @Provides
    fun providePaperListViewModel(context: Context, paperRepository: PaperRepository): PaperListViewModel {
        return PaperListViewModel(context, paperRepository)
    }
}
