package rejasupotaro.arxiv.reader.ui.paper.search

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_paper_search.*
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.extensions.hideKeyboard
import rejasupotaro.arxiv.reader.extensions.showKeyboard

class PaperSearchActivity : LifecycleActivity() {
    private val viewModel = PaperSearchViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paper_search)
        setupViews()
    }

    private fun setupViews() {
        setupSearchResultListView()
        setupSearchView()
    }

    private fun setupSearchView() {
        viewModel.latestQueries().observe(this, Observer<List<String>> { queries ->
            queries?.let {
                val adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, queries)
                queryEditText.setAdapter<ArrayAdapter<String>>(adapter)
                queryEditText.showDropDown()
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
        submitButton.setOnClickListener { doSearch() }
        queryEditText.showKeyboard()
    }

    private fun setupSearchResultListView() {
        val adapter = SearchResultListAdapter(
                onItemClickListener = { paper ->
                    Toast.makeText(this, "Download ${paper.downloadUrl}", Toast.LENGTH_SHORT).show()
                    viewModel.download(this, paper)
                            .observe(this, Observer<Unit> {
                                Toast.makeText(this, "Download complete", Toast.LENGTH_SHORT).show()
                            })
                }
        )

        searchResultListView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.searchResults.observe(this, Observer<List<Paper>> { papers ->
            papers?.let {
                adapter.items = papers.toMutableList()
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
