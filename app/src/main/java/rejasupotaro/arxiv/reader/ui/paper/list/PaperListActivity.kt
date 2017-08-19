package rejasupotaro.arxiv.reader.ui.paper.list

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.transition.Explode
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_paper_list.*
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.ui.common.NavigationController
import javax.inject.Inject

class PaperListActivity : LifecycleActivity() {
    @Inject lateinit var viewModel: PaperListViewModel

    lateinit var adapter: PaperListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
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
                    window.exitTransition = Explode()
                    NavigationController.navigateToReader(this, paper.id)
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
