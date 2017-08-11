package rejasupotaro.arxiv.reader.ui.paper.find

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_search_result.view.*
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.model.Paper

class SearchResultListAdapter(val onItemClickLIstener: (Paper) -> Unit) : RecyclerView.Adapter<SearchResultViewHolder>() {
    var items = listOf<Paper>()
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_search_result, parent, false)
        return SearchResultViewHolder(itemView, onItemClickLIstener)
    }
}

class SearchResultViewHolder(itemView: View, val onItemClickListener: (Paper) -> Unit) : RecyclerView.ViewHolder(itemView) {
    fun bind(paper: Paper) {
        itemView.setOnClickListener { onItemClickListener.invoke(paper) }
        itemView.titleTextView.text = paper.title
    }
}

