package rejasupotaro.arxiv.reader.ui.paper.list

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_search_result.view.*
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.data.model.Paper

class PaperListAdapter(
        val onItemClickListener: (Paper) -> Unit,
        val onItemLongClickListener: (Paper) -> Boolean = { _ -> false }
) : RecyclerView.Adapter<PaperViewHolder>() {
    var items = mutableListOf<Paper>()
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PaperViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaperViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_paper, parent, false)
        return PaperViewHolder(itemView, onItemClickListener, onItemLongClickListener)
    }
}

class PaperViewHolder(
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
