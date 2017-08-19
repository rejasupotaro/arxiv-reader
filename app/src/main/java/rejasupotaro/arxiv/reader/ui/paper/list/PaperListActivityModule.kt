package rejasupotaro.arxiv.reader.ui.paper.list

import dagger.Module
import dagger.Provides
import rejasupotaro.arxiv.reader.data.db.ArxivDb

@Module
class PaperListActivityModule {
    @Provides
    fun providePaperListViewModel(db: ArxivDb): PaperListViewModel {
        return PaperListViewModel(db)
    }
}
