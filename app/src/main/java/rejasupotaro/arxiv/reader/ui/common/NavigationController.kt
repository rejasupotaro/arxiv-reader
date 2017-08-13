package rejasupotaro.arxiv.reader.ui.common

import android.support.v4.app.FragmentManager
import rejasupotaro.arxiv.reader.MainActivity
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.ui.my_paper.list.MyPaperListFragment
import rejasupotaro.arxiv.reader.ui.paper.find.PaperFindFragment
import rejasupotaro.arxiv.reader.ui.paper.view.PaperViewFragmentAutoBundle

object NavigationController {
    val containerId = R.id.content
    lateinit var fragmentManager: FragmentManager

    fun init(activity: MainActivity) {
        fragmentManager = activity.supportFragmentManager
    }

    fun navigateToMyPapers() {
        fragmentManager.beginTransaction()
                .replace(containerId, MyPaperListFragment())
                .commit()
    }

    fun navigateToViewer(paper: Paper? = null) {
        val fragment = PaperViewFragmentAutoBundle.builder()
                .paper(paper)
                .build()
        fragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .commit()
    }

    fun navigateToSearch() {
        fragmentManager.beginTransaction()
                .replace(containerId, PaperFindFragment())
                .commit()
    }
}

