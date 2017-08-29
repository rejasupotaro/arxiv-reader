package rejasupotaro.arxiv.reader.data

import android.content.Context
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.nlp.TfIdf
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
                            val similarity = Pair(papers[j], cosineSimilarity(vectors[i], vectors[j]))
                            similarities.add(similarity)
                        }
                    }
            similarities.sortedByDescending { (_, value) -> value }.take(n)
        }
    }

    fun stopwords(context: Context): List<String> {
        return context.assets.open("ranksnl_large.txt").bufferedReader().use(BufferedReader::readText).split("\n")
    }

    private fun cosineSimilarity(vector1: List<Double>, vector2: List<Double>): Double {
        var dotProduct = 0.0
        var magnitude1 = 0.0
        var magnitude2 = 0.0

        (vector1.indices).map { i ->
            dotProduct += vector1[i] * vector2[i]
            magnitude1 += Math.pow(vector1[i], 2.0)
            magnitude2 += Math.pow(vector2[i], 2.0)
        }

        magnitude1 = Math.sqrt(magnitude1)
        magnitude2 = Math.sqrt(magnitude2)

        return if ((magnitude1 != 0.0) or (magnitude2 != 0.0)) {
            dotProduct / (magnitude1 * magnitude2)
        } else {
            0.0
        }
    }
}

