package rejasupotaro.arxiv.reader.ui.common

import android.content.Context
import android.content.Intent
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.ui.paper.list.PaperListActivity
import rejasupotaro.arxiv.reader.ui.paper.read.PaperReadActivityAutoBundle
import rejasupotaro.arxiv.reader.ui.paper.search.PaperSearchActivity
import rejasupotaro.arxiv.reader.ui.paper.view.PaperViewActivityAutoBundle

object NavigationController {
    fun navigateToMyPapers(context: Context) {
        val intent = Intent(context, PaperListActivity::class.java)
        context.startActivity(intent)
    }

    fun navigateToSearch(context: Context) {
        val intent = Intent(context, PaperSearchActivity::class.java)
        context.startActivity(intent)
    }

    fun navigateToViewer(context: Context, paper: Paper) {
        val intent = PaperViewActivityAutoBundle.builder(paper)
                .build(context)
        context.startActivity(intent)
    }

    fun navigateToReader(context: Context, paperId: Long) {
        val intent = PaperReadActivityAutoBundle.builder(paperId)
                .build(context)
        context.startActivity(intent)
    }
}

