package rejasupotaro.arxiv.reader.ui.paper.list

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_paper_list.*
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.ui.common.NavigationController

class PaperListActivity : LifecycleActivity() {
    private val viewModel = PaperListViewModel()
    lateinit var adapter: PaperListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paper_list)
        setupViews()
    }

    private fun setupViews() {
        setupToolbar()
        setupListView()
    }

    private fun setupToolbar() {
        searchButton.setOnClickListener { NavigationController.navigateToSearch(this) }
    }

    private fun setupListView() {
        adapter = PaperListAdapter(
                onItemClickListener = { paper ->
                    NavigationController.navigateToViewer(this, paper.id)
                },
                onItemLongClickListener = { paper ->
                    viewModel.deletePaper(paper).observe(this, Observer {
                        adapter.items.remove(paper)
                        adapter.notifyDataSetChanged()
                    })
                    true
                }
        )

        paperListView.adapter = adapter
        paperListView.layoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this, (paperListView.layoutManager as LinearLayoutManager).orientation)
        paperListView.addItemDecoration(itemDecoration)

        viewModel.paperList().observe(this, Observer<List<Paper>> { papers ->
            papers?.let {
                adapter.items = papers.toMutableList()
                adapter.notifyDataSetChanged()
            }
        })
    }
}
