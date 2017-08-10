package rejasupotaro.arxiv.reader.ui.paper.find

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import rejasupotaro.arxiv.reader.R
import rejasupotaro.arxiv.reader.api.ApiClient

class PaperFindFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_paper_find, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        object : Thread() {
            override fun run() {
                ApiClient().search()
            }
        }.start()
    }
}
