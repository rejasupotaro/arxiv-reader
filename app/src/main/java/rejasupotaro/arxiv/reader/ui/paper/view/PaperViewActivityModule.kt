package rejasupotaro.arxiv.reader.ui.paper.view

import dagger.Module
import dagger.Provides
import rejasupotaro.arxiv.reader.data.db.ArxivDb

@Module
class PaperViewActivityModule {
    @Provides
    fun providePaperViewViewModel(db: ArxivDb): PaperViewViewModel {
        return PaperViewViewModel(db)
    }
}
