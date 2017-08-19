package rejasupotaro.arxiv.reader.ui.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v4.view.ViewCompat
import android.view.View
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.ui.paper.list.PaperListActivity
import rejasupotaro.arxiv.reader.ui.paper.read.PaperReadActivityAutoBundle
import rejasupotaro.arxiv.reader.ui.paper.search.PaperSearchActivity
import rejasupotaro.arxiv.reader.ui.paper.view.PaperViewActivityAutoBundle
import android.app.ActivityOptions
import android.os.Bundle



object NavigationController {
    fun navigateToMyPapers(context: Context) {
        val intent = Intent(context, PaperListActivity::class.java)
        context.startActivity(intent)
    }

    fun navigateToSearch(context: Context) {
        val intent = Intent(context, PaperSearchActivity::class.java)
        context.startActivity(intent)
    }

    fun navigateToViewer(activity: Activity, paper: Paper, view: View) {
        val transitionName = paper.title
        ViewCompat.setTransitionName(view, paper.title)
        val pair = Pair(view, transitionName)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair)
        val intent = PaperViewActivityAutoBundle.builder(paper, transitionName).build(activity)
        ActivityCompat.startActivity(activity, intent, options.toBundle())
    }

    fun navigateToReader(activity: Activity, paperId: Long) {
        val options = ActivityOptions.makeSceneTransitionAnimation(activity)
        val intent = PaperReadActivityAutoBundle.builder(paperId).build(activity)
        ActivityCompat.startActivity(activity, intent, options.toBundle())
    }
}

