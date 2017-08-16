package rejasupotaro.arxiv.reader.ui.paper.search

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.view_category.view.*
import rejasupotaro.arxiv.reader.R

class CategoryView : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        inflate(context, R.layout.view_category, this)
    }

    var category: String = ""
        set(value) {
            if (categoryTextView.background is GradientDrawable) {
                val drawable = categoryTextView.background as GradientDrawable
                val colorId = if (value.startsWith("Computer Science")) {
                    R.color.ink_orange
                } else {
                    R.color.ink_blue
                }
                drawable.color = ColorStateList.valueOf(ContextCompat.getColor(context, colorId))
            }
            categoryTextView.text = value
        }
}

