package rejasupotaro.arxiv.reader.ui.paper.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.view_similar_paper.view.*
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.extensions.toHtml

class SimilarPaperListAdapter(
        private val onItemClickListener: (Paper, View) -> Unit
) : RecyclerView.Adapter<SimilarPaperViewHolder>() {
    var items = listOf<Pair<Paper, Double>>()

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: SimilarPaperViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarPaperViewHolder {
        return SimilarPaperViewHolder(SimilarPaperView(parent.context), onItemClickListener)
    }
}

class SimilarPaperViewHolder(
        private val similarPaperView: SimilarPaperView,
        private val onItemClickListener: (Paper, View) -> Unit
) : RecyclerView.ViewHolder(similarPaperView) {
    fun bind(similarPaper: Pair<Paper, Double>) {
        similarPaperView.similarPaper = similarPaper
        similarPaperView.setOnClickListener {
            onItemClickListener.invoke(similarPaper.first, similarPaperView.titleTextView)
        }
    }
}

class SimilarPaperView : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        inflate(context, R.layout.view_similar_paper, this)
    }

    var similarPaper: Pair<Paper, Double>? = null
        set(value) {
            value?.let {
                titleTextView.text = "<b>${value.first.title}</b> (${value.second})".toHtml()
            }
        }
}
