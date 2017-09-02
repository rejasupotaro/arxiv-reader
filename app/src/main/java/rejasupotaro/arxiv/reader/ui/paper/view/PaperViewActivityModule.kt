package rejasupotaro.arxiv.reader.ui.paper.view

import android.content.Context
import dagger.Module
import dagger.Provides
import rejasupotaro.arxiv.reader.data.repository.PaperRepository

@Module
class PaperViewActivityModule {
    @Provides
    fun providePaperSearchViewModel(
            context: Context,
            paperRepository: PaperRepository
    ): PaperViewViewModel {
        return PaperViewViewModel(context, paperRepository)
    }
}

