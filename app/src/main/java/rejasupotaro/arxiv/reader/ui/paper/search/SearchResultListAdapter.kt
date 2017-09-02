package rejasupotaro.arxiv.reader.ui.paper.search

import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main.list_item_search_result.view.*
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.ui.common.CategoryListAdapter

class SearchResultListAdapter(
        val onItemClickListener: (View, Paper) -> Unit
) : RecyclerView.Adapter<SearchResultViewHolder>() {
    var items = mutableListOf<Paper>()
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_search_result, parent, false)
        return SearchResultViewHolder(itemView, onItemClickListener)
    }
}

class SearchResultViewHolder(
        itemView: View,
        private val onItemClickListener: (View, Paper) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private var adapter = CategoryListAdapter()

    init {
        itemView.categoryListView.layoutManager = FlexboxLayoutManager(itemView.context).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START
            flexWrap = FlexWrap.WRAP
        }
        itemView.categoryListView.addItemDecoration(DividerItemDecoration(itemView.context, LinearLayoutManager.HORIZONTAL).apply {
            setDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.item_decoration_spacing_horizontal))
        })
        itemView.categoryListView.adapter = adapter
    }

    fun bind(paper: Paper) {
        itemView.titleTextView.text = paper.title
        itemView.downloadUrlTextView.text = "${paper.publishedAt.year} - ${paper.downloadUrl}"
        itemView.summaryTextView.text = paper.summary
        adapter.items = paper.categories
        adapter.notifyDataSetChanged()
        itemView.setOnClickListener { onItemClickListener.invoke(itemView.titleTextView, paper) }
    }
}
