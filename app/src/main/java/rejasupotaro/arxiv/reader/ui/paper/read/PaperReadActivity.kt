package rejasupotaro.arxiv.reader.ui.paper.read

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.transition.Fade
import com.yatatsu.autobundle.AutoBundleField
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_paper_read.*
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.data.file.FileManager
import javax.inject.Inject

class PaperReadActivity : LifecycleActivity() {
    @Inject lateinit var viewModel: PaperReadViewModel
    @AutoBundleField
    var paperId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paper_read)
        PaperReadActivityAutoBundle.bind(this, intent)
        window.enterTransition = Fade()
        setupPdfView()
    }

    private fun setupPdfView() {
        viewModel.paper.observe(this, Observer { paper ->
            paper?.let {
                val file = FileManager.paperToFile(this, it)
                pdfView.fromFile(file)
                        .defaultPage(it.lastOpenedPage)
                        .onPageChange { page, pageCount ->
                            viewModel.updatePaperLastOpenedPage(page, pageCount)?.observe(this, Observer {})
                        }
                        .load()
            }
        })
        viewModel.loadPaper(paperId)
    }
}
