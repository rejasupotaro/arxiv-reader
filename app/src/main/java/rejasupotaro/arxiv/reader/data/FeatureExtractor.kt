package rejasupotaro.arxiv.reader.data

import android.content.Context
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.nlp.TfIdf
import rejasupotaro.nlp.cosineSimilarity
import rejasupotaro.nlp.tokenize
import java.io.BufferedReader

object FeatureExtractor {
    fun topNWords(context: Context, papers: List<Paper>, n: Int = 10): List<List<Pair<String, Double>>> {
        val docs = papers.map { (title, summary) ->
            "$title $summary".tokenize()
        }
        val vectors = TfIdf.vectorize(docs, stopwords(context))
        return vectors.map { vector ->
            vector.toList().sortedByDescending { (_, value) -> value }.take(n)
        }
    }

    fun topNSimilarPapers(context: Context, papers: List<Paper>, n: Int = 10): List<List<Pair<Paper, Double>>> {
        val vectors: List<List<Double>> = FeatureExtractor.topNWords(context, papers)
                .map { vector ->
                    vector.map { it.second }
                }
        return (vectors.indices).map { i ->
            val similarities = mutableListOf<Pair<Paper, Double>>()
            (vectors.indices)
                    .forEach { j ->
                        if (i != j) {
                            val similarity = cosineSimilarity(vectors[i].toDoubleArray(), vectors[j].toDoubleArray())
                            similarities.add(Pair(papers[j], similarity))
                        }
                    }
            similarities.sortedByDescending { (_, value) -> value }.take(n)
        }
    }

    fun stopwords(context: Context): List<String> {
        return context.assets.open("ranksnl_large.txt").bufferedReader().use(BufferedReader::readText).split("\n")
    }
}

