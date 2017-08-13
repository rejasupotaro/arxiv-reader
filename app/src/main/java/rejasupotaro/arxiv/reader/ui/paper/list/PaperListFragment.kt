package rejasupotaro.arxiv.reader.ui.paper.list

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_paper_list.*
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.ui.common.NavigationController
import rejasupotaro.arxiv.reader.ui.paper.search.SearchResultListAdapter

class PaperListFragment : LifecycleFragment() {
    private val viewModel = PaperListViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_paper_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupListView()
    }

    private fun setupListView() {
        val adapter = SearchResultListAdapter { paper ->
            NavigationController.navigateToViewer(paper)
        }

        paperListView.adapter = adapter
        paperListView.layoutManager = GridLayoutManager(context, 4)

        viewModel.paperList().observe(this, Observer<List<Paper>> { papers ->
            papers?.let {
                adapter.items = papers
                adapter.notifyDataSetChanged()
            }
        })
    }
}
