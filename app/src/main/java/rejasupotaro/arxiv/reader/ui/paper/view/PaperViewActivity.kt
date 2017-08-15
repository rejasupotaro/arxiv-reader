package rejasupotaro.arxiv.reader.ui.paper.view

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.os.Bundle
import com.yatatsu.autobundle.AutoBundleField
import kotlinx.android.synthetic.main.activity_paper_view.*
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.data.file.FileManager

class PaperViewActivity : LifecycleActivity() {
    @AutoBundleField
    var paperId: Long = 0

    private lateinit var viewModel: PaperViewViewModel

    private val onPageChangedListener = { page: Int, _: Int ->
        viewModel.updatePaperLastOpenedPage(page).observe(this, Observer {})
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paper_view)
        PaperViewActivityAutoBundle.bind(this, intent)
        viewModel = PaperViewViewModel(paperId)
        setupPdfView()
    }

    private fun setupPdfView() {
        viewModel.paper.observe(this, Observer { paper ->
            paper?.let {
                val file = FileManager.paperToFile(this, it)
                pdfView.fromFile(file)
                        .defaultPage(it.lastOpenedPage)
                        .onPageChange(onPageChangedListener)
                        .load()
            }
        })
    }
}
