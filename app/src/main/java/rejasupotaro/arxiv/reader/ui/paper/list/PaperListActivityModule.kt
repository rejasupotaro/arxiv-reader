package rejasupotaro.arxiv.reader.ui.paper.list

import dagger.Module
import dagger.Provides
import rejasupotaro.arxiv.reader.data.repository.PaperRepository

@Module
class PaperListActivityModule {
    @Provides
    fun providePaperListViewModel(paperRepository: PaperRepository): PaperListViewModel {
        return PaperListViewModel(paperRepository)
    }
}
