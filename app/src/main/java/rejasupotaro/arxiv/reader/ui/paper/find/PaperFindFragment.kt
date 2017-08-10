package rejasupotaro.arxiv.reader.ui.paper.find

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.api.ApiClient
import rejasupotaro.arxiv.reader.model.Paper

class PaperFindFragment : LifecycleFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_paper_find, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ApiClient().search().observe(this, Observer<List<Paper>> { data ->
            Toast.makeText(activity, "${data?.size} found", Toast.LENGTH_SHORT).show()
        })
    }
}
