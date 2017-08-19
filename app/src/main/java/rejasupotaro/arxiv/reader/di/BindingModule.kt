package rejasupotaro.arxiv.reader.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import rejasupotaro.arxiv.reader.job.PdfDownloadService
import rejasupotaro.arxiv.reader.ui.paper.list.PaperListActivity
import rejasupotaro.arxiv.reader.ui.paper.list.PaperListActivityModule
import rejasupotaro.arxiv.reader.ui.paper.search.PaperSearchActivity
import rejasupotaro.arxiv.reader.ui.paper.search.PaperSearchActivityModule
import rejasupotaro.arxiv.reader.ui.paper.read.PaperReadActivity
import rejasupotaro.arxiv.reader.ui.paper.read.PaperReadActivityModule

@Module
abstract class BindingModule {
    @ContributesAndroidInjector(
            modules = arrayOf(
                    PaperListActivityModule::class
            )
    )
    abstract fun bindPaperListActivity(): PaperListActivity

    @ContributesAndroidInjector(
            modules = arrayOf(
                    PaperSearchActivityModule::class
            )
    )
    abstract fun bindPaperSearchActivity(): PaperSearchActivity

    @ContributesAndroidInjector(
            modules = arrayOf(
                    PaperReadActivityModule::class
            )
    )
    abstract fun bindPaperViewActivity(): PaperReadActivity

    @ContributesAndroidInjector
    abstract fun bindPdfDownloadService(): PdfDownloadService
}
