package rejasupotaro.arxiv.reader.ui.paper.search

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.KeyEvent
import android.widget.ArrayAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_paper_search.*
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.extensions.hideKeyboard
import rejasupotaro.arxiv.reader.extensions.showKeyboard
import rejasupotaro.arxiv.reader.ui.common.NavigationController
import javax.inject.Inject

class PaperSearchActivity : LifecycleActivity() {
    @Inject lateinit var viewModel: PaperSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paper_search)
        setupViews()
    }

    private fun setupViews() {
        setupSearchView()
        setupSearchResultListView()
    }

    private fun setupSearchView() {
        viewModel.latestQueries().observe(this, Observer<List<String>> { queries ->
            queries?.let {
                val adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, queries)
                queryEditText.post {
                    queryEditText.setAdapter(adapter)
                    queryEditText.showDropDown()
                }
            }
        })
        queryEditText.setOnItemClickListener { _, _, _, _ -> doSearch() }
        queryEditText.setOnKeyListener { _, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                doSearch()
                true
            } else {
                false
            }
        }
        searchButton.setOnClickListener { doSearch() }
        queryEditText.showKeyboard()
    }

    private fun setupSearchResultListView() {
        val adapter = SearchResultListAdapter(
                onItemClickListener = { paper ->
                    NavigationController.navigateToViewer(this, paper)
                }
        )

        searchResultListView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context)
        }

        searchResultListView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                if (lastPosition == adapter.itemCount - 1) {
                    viewModel.loadNextPage()
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

        viewModel.searchResults.observe(this, Observer<SearchResponse> { response ->
            response?.let {
                if (it.page == 1) {
                    adapter.items = response.result.toMutableList()
                } else {
                    adapter.items.addAll(response.result.toMutableList())
                }
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun doSearch() {
        queryEditText.hideKeyboard()
        val query = queryEditText.text.toString().trim()
        viewModel.query = query
    }
}
