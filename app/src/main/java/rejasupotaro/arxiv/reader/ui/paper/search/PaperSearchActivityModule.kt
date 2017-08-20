package rejasupotaro.arxiv.reader.ui.paper.search

import dagger.Module
import dagger.Provides
import rejasupotaro.arxiv.reader.data.repository.PaperRepository
import rejasupotaro.arxiv.reader.data.repository.SearchHistoryRepository

@Module
class PaperSearchActivityModule {
    @Provides
    fun providePaperSearchViewModel(
            paperRepository: PaperRepository,
            searchHistoryRepository: SearchHistoryRepository
    ): PaperSearchViewModel {
        return PaperSearchViewModel(paperRepository, searchHistoryRepository)
    }
}
