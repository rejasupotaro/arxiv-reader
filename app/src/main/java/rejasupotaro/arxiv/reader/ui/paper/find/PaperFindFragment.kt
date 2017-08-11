package rejasupotaro.arxiv.reader.ui.paper.find

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_paper_find.*
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.model.Paper

class PaperFindFragment : LifecycleFragment() {
    private val viewModel = PaperFindViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_paper_find, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        queryEditText.setOnKeyListener { _, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                doSearch()
                true
            } else {
                false
            }
        }
        submitButton.setOnClickListener { doSearch() }

        viewModel.searchResults.observe(this, Observer<List<Paper>> { papers ->
            papers?.let { showSearchResults(it) }
        })
    }

    private fun doSearch() {
        val query = queryEditText.text.toString().trim()
        viewModel.query = query
    }

    private fun showSearchResults(papers: List<Paper>) {
        Toast.makeText(activity, "${papers.size} results found", Toast.LENGTH_SHORT).show()
    }
}
