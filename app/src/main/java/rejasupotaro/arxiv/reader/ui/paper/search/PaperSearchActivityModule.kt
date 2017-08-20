package rejasupotaro.arxiv.reader.ui.paper.search

import dagger.Module
import dagger.Provides
import rejasupotaro.arxiv.reader.data.repo.PaperRepository
import rejasupotaro.arxiv.reader.data.repo.SearchHistoryRepository

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
