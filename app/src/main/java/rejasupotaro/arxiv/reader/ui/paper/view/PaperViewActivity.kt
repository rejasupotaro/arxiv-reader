package rejasupotaro.arxiv.reader.ui.paper.view

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.os.Bundle
import com.yatatsu.autobundle.AutoBundleField
import kotlinx.android.synthetic.main.activity_paper_view.*
import rejasupotaro.arxiv.reader.R
import java.io.File

class PaperViewActivity : LifecycleActivity() {
    @AutoBundleField
    var paperId: Long = 0

    private lateinit var viewModel: PaperViewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paper_view)
        PaperViewActivityAutoBundle.bind(this, intent)
        viewModel = PaperViewViewModel(this, paperId)
        setupPdfView()
    }

    private fun setupPdfView() {
        viewModel.loadPaper().observe(this, Observer<File> {
            pdfView.fromFile(it)
                    .load()
        })
    }
}
