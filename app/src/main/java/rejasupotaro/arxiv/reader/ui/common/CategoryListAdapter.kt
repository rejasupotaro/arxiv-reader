package rejasupotaro.arxiv.reader.ui.common

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import rejasupotaro.arxiv.reader.data.model.Category
import rejasupotaro.arxiv.reader.ui.paper.search.CategoryView

class CategoryListAdapter : RecyclerView.Adapter<CategoryViewHolder>() {
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

class CategoryViewHolder(val categoryView: CategoryView) : RecyclerView.ViewHolder(categoryView) {
    fun bind(category: Category) {
        categoryView.category = category
    }
}
