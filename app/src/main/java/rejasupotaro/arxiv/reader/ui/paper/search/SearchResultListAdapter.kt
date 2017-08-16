package rejasupotaro.arxiv.reader.ui.paper.search

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
import rejasupotaro.arxiv.reader.data.model.Category
import rejasupotaro.arxiv.reader.data.model.Paper

class SearchResultListAdapter(
        val onItemClickListener: (Paper) -> Unit
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
        val onItemClickListener: (Paper) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private var adapter = CategoryListAdapter()

    init {
        val layoutManager = FlexboxLayoutManager(itemView.context).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START
            flexWrap = FlexWrap.WRAP
        }
        itemView.categoryListView.layoutManager = layoutManager
        itemView.categoryListView.adapter = adapter
    }

    fun bind(paper: Paper) {
        itemView.titleTextView.text = paper.title
        itemView.downloadUrlTextView.text = paper.downloadUrl
        itemView.summaryTextView.text = paper.summary
        adapter.items = paper.categories
        adapter.notifyDataSetChanged()
        itemView.setOnClickListener { onItemClickListener.invoke(paper) }
    }
}

private class CategoryListAdapter : RecyclerView.Adapter<CategoryViewHolder>() {
    var items = listOf<Category>()
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val categoryView = CategoryView(parent.context)
        return CategoryViewHolder(categoryView)
    }
}

private class CategoryViewHolder(val categoryView: CategoryView) : RecyclerView.ViewHolder(categoryView) {
    fun bind(category: Category) {
        categoryView.category = category
    }
}
