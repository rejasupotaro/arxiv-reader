package rejasupotaro.arxiv.reader.ui.paper.view

import android.arch.lifecycle.LifecycleActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.yatatsu.autobundle.AutoBundleField
import kotlinx.android.synthetic.main.activity_paper_view.*
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.model.PaperConverter
import rejasupotaro.arxiv.reader.job.PdfDownloadServiceAutoBundle
import rejasupotaro.arxiv.reader.ui.common.CategoryListAdapter

class PaperViewActivity : LifecycleActivity() {
    @AutoBundleField(converter = PaperConverter::class)
    lateinit var paper: Paper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paper_view)
        PaperViewActivityAutoBundle.bind(this, intent)
        setupViews()
    }

    private fun setupViews() {
        titleTextView.text = paper.title
        authorsTextView.text = paper.authors.joinToString()
        summaryTextView.text = paper.summary
        categoryListView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        categoryListView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL).apply {
            setDrawable(ContextCompat.getDrawable(this@PaperViewActivity, R.drawable.item_decoration_spacing_horizontal))
        })
        categoryListView.adapter = CategoryListAdapter().apply {
            items = paper.categories
        }
        downloadButton.setOnClickListener {
            val intent = PdfDownloadServiceAutoBundle.builder(paper).build(this)
            startService(intent)
        }
    }
}
