package rejasupotaro.arxiv.reader.ui.common

import android.content.Context
import android.content.Intent
import rejasupotaro.arxiv.reader.ui.paper.list.PaperListActivity
import rejasupotaro.arxiv.reader.ui.paper.search.PaperSearchActivity
import rejasupotaro.arxiv.reader.ui.paper.view.PaperViewActivityAutoBundle

object NavigationController {
    fun navigateToMyPapers(context: Context) {
        val intent = Intent(context, PaperListActivity::class.java)
        context.startActivity(intent)
    }

    fun navigateToViewer(context: Context, paperId: Long) {
        val intent = PaperViewActivityAutoBundle.builder(paperId)
                .build(context)
        context.startActivity(intent)
    }

    fun navigateToSearch(context: Context) {
        val intent = Intent(context, PaperSearchActivity::class.java)
        context.startActivity(intent)
    }
}

