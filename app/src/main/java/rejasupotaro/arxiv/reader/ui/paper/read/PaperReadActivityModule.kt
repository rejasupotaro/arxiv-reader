package rejasupotaro.arxiv.reader.ui.paper.read

import dagger.Module
import dagger.Provides
import rejasupotaro.arxiv.reader.data.db.ArxivDb

@Module
class PaperReadActivityModule {
    @Provides
    fun providePaperViewViewModel(db: ArxivDb): PaperReadViewModel {
        return PaperReadViewModel(db)
    }
}
