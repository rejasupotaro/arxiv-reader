package rejasupotaro.arxiv.reader.ui.paper.search

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.view_category.view.*
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.data.model.Category

class CategoryView : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        inflate(context, R.layout.view_category, this)
    }

    var category: Category = Category("", "", R.color.md_white_1000)
        set(value) {
            if (categoryTextView.background is GradientDrawable) {
                val drawable = categoryTextView.background as GradientDrawable
                drawable.color = ColorStateList.valueOf(ContextCompat.getColor(context, value.color))
            }
            categoryTextView.text = value.text
        }
}

