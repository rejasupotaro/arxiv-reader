package rejasupotaro.arxiv.reader.ui.paper.view

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.transition.Explode
import android.transition.Fade
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.yatatsu.autobundle.AutoBundleField
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_paper_view.*
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.model.PaperConverter
import rejasupotaro.arxiv.reader.extensions.readableText
import rejasupotaro.arxiv.reader.job.PdfDownloadServiceAutoBundle
import rejasupotaro.arxiv.reader.ui.common.CategoryListAdapter
import rejasupotaro.arxiv.reader.ui.common.NavigationController
import javax.inject.Inject

class PaperViewActivity : LifecycleActivity() {
    @AutoBundleField(converter = PaperConverter::class) lateinit var paper: Paper
    @AutoBundleField lateinit var transitionName: String
    @Inject lateinit var viewModel: PaperViewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
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
    }

    private fun setupViews() {
        authorsTextView.text = paper.authors.joinToString()
        publishedAtTextView.text = paper.publishedAt.readableText()
        summaryTextView.text = paper.summary
        setupCategoryListView()
        setupSimilarPaperListView()
    }

    private fun setupCategoryListView() {
        categoryListView.layoutManager = FlexboxLayoutManager(this).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START
            flexWrap = FlexWrap.WRAP
        }

        categoryListView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL).apply {
            setDrawable(ContextCompat.getDrawable(this@PaperViewActivity, R.drawable.item_decoration_spacing_horizontal))
        })
        categoryListView.adapter = CategoryListAdapter().apply {
            items = paper.categories
        }

        viewModel.loadPaper(paper.title).observe(this, Observer { paper ->
            if (paper == null) {
                setDownloadAction()
            } else {
                setReadAction(paper)
            }
        })
    }

    private fun setDownloadAction() {
        actionButton.text = getString(R.string.button_download)
        actionButton.setOnClickListener {
            val intent = PdfDownloadServiceAutoBundle.builder(paper).build(this)
            startService(intent)
        }
    }

    private fun setReadAction(paper: Paper) {
        actionButton.text = getString(R.string.button_read)
        actionButton.setOnClickListener {
            NavigationController.navigateToReader(this, paper.id)
        }
    }

    private fun setupSimilarPaperListView() {
        val adapter = SimilarPaperListAdapter { paper, view ->
            NavigationController.navigateToViewer(this, paper, view)
        }

        similarPaperListView.layoutManager = LinearLayoutManager(this)
        similarPaperListView.adapter = adapter

        viewModel.loadSimilarPapers(paper.title).observe(this, Observer { similarPapers ->
            similarPapers?.let {
                adapter.items = similarPapers
                adapter.notifyDataSetChanged()
            }
        })
    }
}
