package rejasupotaro.arxiv.reader.ui.paper.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yatatsu.autobundle.AutoBundleField
import kotlinx.android.synthetic.main.fragment_paper_view.*
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.model.PaperConverter
import java.io.File

class PaperViewFragment : Fragment() {
    @AutoBundleField(converter = PaperConverter::class, required = false)
    var paper: Paper? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_paper_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        PaperViewFragmentAutoBundle.bind(this)
        setupPdfView()
    }

    private fun setupPdfView() {
        paper?.let {
            val file = File(context.filesDir, it.title)
           pdfView.fromFile(file).load()
        } ?: run {
            pdfView.fromAsset("example.pdf").load()
        }
    }
}
