package rejasupotaro.arxiv.reader.ui.paper.search

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_paper_search.*
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.data.model.Paper

class PaperSearchFragment : LifecycleFragment() {
    private val viewModel = PaperSearchViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_paper_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupSearchView()
        setupSearchResultListView()
    }

    private fun setupSearchView() {
        queryEditText.setOnKeyListener { _, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                doSearch()
                true
            } else {
                false
            }
        }
        submitButton.setOnClickListener { doSearch() }
    }

    private fun setupSearchResultListView() {
        val adapter = SearchResultListAdapter { paper ->
            Toast.makeText(context, "Download ${paper.downloadUrl}", Toast.LENGTH_SHORT).show()
            viewModel.download(context, paper).observe(this, Observer<Unit> {
                Toast.makeText(context, "Download complete", Toast.LENGTH_SHORT).show()
            })
        }

        searchResultListView.adapter = adapter
        searchResultListView.layoutManager = LinearLayoutManager(activity)
        viewModel.searchResults.observe(this, Observer<List<Paper>> { papers ->
            papers?.let {
                showSearchResults(it)
                adapter.items = papers
                adapter.notifyDataSetChanged()
            }
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