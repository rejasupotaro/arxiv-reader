package rejasupotaro.arxiv.reader.data

import android.content.Context
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.nlp.TfIdf
import rejasupotaro.nlp.cosineSimilarity
import rejasupotaro.nlp.tokenize
import java.io.BufferedReader

object FeatureExtractor {
    // Map<paper.id, List<Pair<word, value>>>
    fun topNWords(context: Context, papers: List<Paper>, n: Int = 10): Map<Long, List<Pair<String, Double>>> {
        val result = mutableMapOf<Long, List<Pair<String, Double>>>()
        vectorize(papers, stopwords(context)).forEachIndexed { index, vector ->
            result.put(papers[index].id, vector.toList().sortedByDescending { (_, value) -> value }.take(n))
        }
        return result
    }

    // Map<paper.id, List<Pair<paper.id, similarity>>>
    fun topNSimilarPapers(context: Context, papers: List<Paper>, n: Int = 10): Map<Long, List<Pair<Long, Double>>> {
        val vectors = vectorize(papers, stopwords(context)).map { it.values }

        val result = mutableMapOf<Long, List<Pair<Long, Double>>>()
        (vectors.indices).forEach { i ->
            val similarities = mutableListOf<Pair<Long, Double>>()
            (vectors.indices).forEach { j ->
                if (i != j) {
                    val similarity = cosineSimilarity(vectors[i].toDoubleArray(), vectors[j].toDoubleArray())
                    similarities.add(Pair(papers[j].id, similarity))
                }
            }
            result.put(papers[i].id, similarities.sortedByDescending { (_, value) -> value }.take(n))
        }
        return result
    }

    // List<Map<word, value>>
    fun vectorize(papers: List<Paper>, stopwords: List<String>): List<Map<String, Double>> {
        val docs = papers.map { (title, summary) ->
            "$title $summary".tokenize()
        }
        return TfIdf.vectorize(docs, stopwords)
    }

    fun stopwords(context: Context): List<String> {
        return context.assets.open("ranksnl_large.txt")
                .bufferedReader()
                .use(BufferedReader::readText)
                .split("\n")
    }
}

