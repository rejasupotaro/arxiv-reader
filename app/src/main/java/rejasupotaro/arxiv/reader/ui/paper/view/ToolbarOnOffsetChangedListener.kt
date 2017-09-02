package rejasupotaro.arxiv.reader.ui.paper.view

import android.support.design.widget.AppBarLayout
import kotlinx.android.synthetic.main.activity_paper_view.*

class ToolbarOnOffsetChangedListener(private val activity: PaperViewActivity) : AppBarLayout.OnOffsetChangedListener {
    private val threshold = 260F

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        val alpha = 1F - Math.min(1F, Math.abs(verticalOffset) / threshold)
        activity.titleTextView.alpha = alpha
        if (alpha < 0.1) {
            activity.titleTextViewInToolbar.animate().alpha(1F)
        } else {
            activity.titleTextViewInToolbar.animate().alpha(0F)
        }
    }
}

