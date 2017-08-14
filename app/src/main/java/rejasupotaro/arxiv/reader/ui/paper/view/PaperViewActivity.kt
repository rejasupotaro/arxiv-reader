package rejasupotaro.arxiv.reader.ui.paper.view

import android.arch.lifecycle.LifecycleActivity
import android.os.Bundle
import com.yatatsu.autobundle.AutoBundleField
import kotlinx.android.synthetic.main.activity_paper_view.*
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.data.file.FileManager
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.model.PaperConverter

class PaperViewActivity : LifecycleActivity() {
    @AutoBundleField(converter = PaperConverter::class, required = false)
    var paper: Paper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paper_view)
        PaperViewActivityAutoBundle.bind(this, intent)
        setupPdfView()
    }

    private fun setupPdfView() {
        paper?.let {
            val file = FileManager.paperToFile(this, it)
            pdfView.fromFile(file).load()
        } ?: run {
            pdfView.fromAsset("example.pdf").load()
        }
    }
}
