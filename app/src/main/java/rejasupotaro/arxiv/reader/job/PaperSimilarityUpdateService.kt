package rejasupotaro.arxiv.reader.job

import android.app.IntentService
import android.content.Intent
import dagger.android.AndroidInjection
import rejasupotaro.arxiv.reader.data.FeatureExtractor
import rejasupotaro.arxiv.reader.data.db.ArxivDb
import rejasupotaro.arxiv.reader.data.model.PaperSimilarity
import javax.inject.Inject

class PaperSimilarityUpdateService(name: String = PaperSimilarityUpdateService::class.java.simpleName) : IntentService(name) {
    @Inject lateinit var db: ArxivDb

    override fun onHandleIntent(intent: Intent) {
        AndroidInjection.inject(this)
        update()
    }

    fun update() {
        val papers = db.paperDao().all()
        FeatureExtractor.topNSimilarPapers(applicationContext, papers).map { (paperId, similarPapers) ->
            val paperSimilarities = similarPapers.map { (similarPaperId, similarity) ->
                PaperSimilarity(paperId, similarPaperId, similarity)
            }
            db.paperSimilarityDao().insertAll(paperSimilarities)
        }
    }
}
