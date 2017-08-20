package rejasupotaro.arxiv.reader.ui.paper.list

import dagger.Module
import dagger.Provides
import rejasupotaro.arxiv.reader.data.db.ArxivDb
import rejasupotaro.arxiv.reader.data.repo.PaperRepository

@Module
class PaperListActivityModule {
    @Provides
    fun providePaperListViewModel(paperRepository: PaperRepository): PaperListViewModel {
        return PaperListViewModel(paperRepository)
    }
}
