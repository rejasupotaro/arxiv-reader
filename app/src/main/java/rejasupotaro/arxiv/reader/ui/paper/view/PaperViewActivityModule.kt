package rejasupotaro.arxiv.reader.ui.paper.view

import dagger.Module
import dagger.Provides
import rejasupotaro.arxiv.reader.data.repository.PaperRepository

@Module
class PaperViewActivityModule {
    @Provides
    fun providePaperSearchViewModel(
            paperRepository: PaperRepository
    ): PaperViewViewModel {
        return PaperViewViewModel(paperRepository)
    }
}

