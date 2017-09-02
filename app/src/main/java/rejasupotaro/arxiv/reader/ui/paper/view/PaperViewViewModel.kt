package rejasupotaro.arxiv.reader.ui.paper.view

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import rejasupotaro.arxiv.reader.data.FeatureExtractor
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.repository.PaperRepository
import rejasupotaro.arxiv.reader.extensions.map
import rejasupotaro.arxiv.reader.extensions.observable
import rejasupotaro.arxiv.reader.extensions.switchMap

class PaperViewViewModel(
        private val context: Context,
        private val paperRepository: PaperRepository
) : ViewModel() {
    val paper = MutableLiveData<Paper>()

    val featureWords = paper.switchMap { paper ->
        paperRepository.all().map { papers ->
            paper?.let {
                FeatureExtractor.topNWords(context, papers, n = 5)[paper.id]
            }
        }
    }

    val similarPapers = paper.switchMap { paper ->
        paper?.let {
            paperRepository.similarPapers(paper.id)
        } ?: observable {
            listOf<Pair<Paper, Double>>()
        }
    }

    fun loadPaper(title: String): LiveData<Unit?> {
        return paperRepository.findByTitle(title).map {
            it?.let {
                paper.value = it
            }
        }
    }
}

