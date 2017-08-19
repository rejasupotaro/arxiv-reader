package rejasupotaro.arxiv.reader.ui.paper.search

import dagger.Module
import dagger.Provides
import rejasupotaro.arxiv.reader.data.db.ArxivDb
import rejasupotaro.arxiv.reader.data.repo.PaperRepository

@Module
class PaperSearchActivityModule {
    @Provides
    fun providePaperSearchViewModel(paperRepository: PaperRepository, db: ArxivDb): PaperSearchViewModel {
        return PaperSearchViewModel(paperRepository, db)
    }
}
