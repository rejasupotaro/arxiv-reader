package rejasupotaro.arxiv.reader.data

import android.content.Context
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.nlp.TfIdf
import rejasupotaro.nlp.tokenize
import java.io.BufferedReader

object FeatureExtractor {
    fun topNWords(context: Context, papers: List<Paper>, n: Int = 10): List<List<Pair<String, Double>>> {
        val docs = papers
                .map { (title, summary) ->
                    "$title $summary".tokenize()
                }
        val vectors: List<Map<String, Double>> = TfIdf.vectorize(docs, stopwords(context))
        return vectors.map { vector ->
            vector.toList().sortedByDescending { (key, value) -> value }.take(n)
        }
    }

    fun stopwords(context: Context): List<String> {
        return context.assets.open("ranksnl_large.txt").bufferedReader().use(BufferedReader::readText).split("\n")
    }
}

