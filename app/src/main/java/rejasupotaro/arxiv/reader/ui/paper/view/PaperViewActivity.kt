package rejasupotaro.arxiv.reader.ui.paper.view

import android.arch.lifecycle.LifecycleActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.transition.Explode
import android.transition.Fade
import com.yatatsu.autobundle.AutoBundleField
import kotlinx.android.synthetic.main.activity_paper_view.*
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.model.PaperConverter
import rejasupotaro.arxiv.reader.extensions.readableText
import rejasupotaro.arxiv.reader.job.PdfDownloadServiceAutoBundle
import rejasupotaro.arxiv.reader.ui.common.CategoryListAdapter

class PaperViewActivity : LifecycleActivity() {
    @AutoBundleField(converter = PaperConverter::class)
    lateinit var paper: Paper

    @AutoBundleField
    lateinit var transitionName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paper_view)
        PaperViewActivityAutoBundle.bind(this, intent)
        ViewCompat.setTransitionName(titleTextView, paper.title)
        window.enterTransition = Explode()
        window.exitTransition = Fade()
        setupToolbar()
        setupViews()
    }

    private fun setupToolbar() {
        upButton.setOnClickListener { onBackPressed() }
        titleTextView.text = paper.title
        titleTextViewInToolbar.text = paper.title
        appBarLayout.addOnOffsetChangedListener(ToolbarOnOffsetChangedListener(this))
    }

    private fun setupViews() {
        authorsTextView.text = paper.authors.joinToString()
        publishedAtTextView.text = paper.publishedAt.readableText()
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

private class ToolbarOnOffsetChangedListener(private val activity: PaperViewActivity) : AppBarLayout.OnOffsetChangedListener {
    val threshold = 260F

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        val alpha = 1F - Math.min(1F, Math.abs(verticalOffset) / threshold)
        activity.titleTextView.alpha = alpha
        if (alpha < 0.1) {
            activity.titleTextViewInToolbar.animate().alpha(1F)
        } else {
            activity.titleTextViewInToolbar.animate().alpha(0F)
        }
    }
}
