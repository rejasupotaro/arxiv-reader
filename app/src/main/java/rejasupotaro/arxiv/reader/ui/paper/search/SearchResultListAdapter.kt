package rejasupotaro.arxiv.reader.ui.paper.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_search_result.view.*
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.data.model.Paper

class SearchResultListAdapter(
        val onItemClickListener: (Paper) -> Unit,
        val onItemLongClickListener: (Paper) -> Boolean = { _ -> false }
) : RecyclerView.Adapter<SearchResultViewHolder>() {
    var items = mutableListOf<Paper>()
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_search_result, parent, false)
        return SearchResultViewHolder(itemView, onItemClickListener, onItemLongClickListener)
    }
}

class SearchResultViewHolder(
        itemView: View,
        val onItemClickListener: (Paper) -> Unit,
        val onItemLongClickListener: (Paper) -> Boolean
) : RecyclerView.ViewHolder(itemView) {
    fun bind(paper: Paper) {
        itemView.titleTextView.text = paper.title
        itemView.summaryTextView.text = paper.summary
        itemView.setOnClickListener { onItemClickListener.invoke(paper) }
        itemView.setOnLongClickListener { onItemLongClickListener.invoke(paper) }
    }
}

