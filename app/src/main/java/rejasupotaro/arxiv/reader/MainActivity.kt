package rejasupotaro.arxiv.reader

import android.arch.lifecycle.LifecycleActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import rejasupotaro.arxiv.reader.ui.common.NavigationController

class MainActivity : LifecycleActivity() {
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_my_papers -> {
                NavigationController.navigateToMyPapers()
                true
            }
            R.id.navigation_viewer -> {
                NavigationController.navigateToViewer()
                true
            }
            R.id.navigation_find_papers -> {
                NavigationController.navigateToSearch()
                true
            }
            else -> false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationController.init(this)
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
