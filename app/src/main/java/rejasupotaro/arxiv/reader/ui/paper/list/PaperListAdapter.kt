package rejasupotaro.arxiv.reader.ui.paper.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import kotlinx.android.synthetic.main.list_item_paper.view.*
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.extensions.relativeTimeSpanText

class PaperListAdapter(
        val onItemClickListener: (Paper, View) -> Unit,
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
        val onItemClickListener: (Paper, View) -> Unit,
        val onItemLongClickListener: (Paper) -> Boolean
) : RecyclerView.ViewHolder(itemView) {
    fun bind(paper: Paper) {
        itemView.apply {
            titleTextView.text = paper.title
            authorsTextView.text = paper.authors.joinToString()
            val relativeTimeText = paper.openedAt?.let {
                context.getString(R.string.opened_xxx_ago, it.relativeTimeSpanText())
            } ?: run {
                context.getString(R.string.downloaded_xxx_ago, paper.downloadedAt.relativeTimeSpanText())
            }
            relativeTimeTextView.text = relativeTimeText
            setOnClickListener { onItemClickListener.invoke(paper, titleTextView) }
            setOnLongClickListener { onItemLongClickListener.invoke(paper) }

            if (paper.lastOpenedPage == 0) {
                readRateIndicatorView.animate().scaleX(1F)
            } else {
                post {
                    readRateIndicatorView.pivotX = readRateIndicatorBackgroundView.measuredWidth.toFloat()
                    val percentage = paper.lastOpenedPage / (paper.totalPage - 1).toFloat()
                    readRateIndicatorView.animate()
                            .setDuration(1000)
                            .setInterpolator(DecelerateInterpolator(3.0F))
                            .scaleX(1 - percentage)
                }
            }
        }
    }
}

