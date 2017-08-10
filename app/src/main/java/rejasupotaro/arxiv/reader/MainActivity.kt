package rejasupotaro.arxiv.reader

import android.arch.lifecycle.LifecycleActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import rejasupotaro.arxiv.reader.ui.paper.find.PaperFindFragment
import rejasupotaro.arxiv.reader.ui.paper.view.PaperViewFragment

class MainActivity : LifecycleActivity() {
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                openPaperViewFragment()
                true
            }
            R.id.navigation_dashboard -> {
                openPaperViewFragment()
                true
            }
            R.id.navigation_notifications -> {
                openPaperFindFragment()
                true
            }
            else -> false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        openPaperViewFragment()
    }

    private fun openPaperViewFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, PaperViewFragment())
                .commit()
    }

    private fun openPaperFindFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, PaperFindFragment())
                .commit()
    }
}
