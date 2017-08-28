package rejasupotaro.arxiv.reader.data

import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.nlp.TfIdf

object FeatureExtractor {
    fun topNWords(papers: List<Paper>, n: Int = 5): List<List<Pair<String, Double>>> {
        val docs = papers
                .map { paper ->
                    paper.summary.split(" ").map { it.toLowerCase() }.sorted()
                }
        val vectors: List<Map<String, Double>> = TfIdf.vectorize(docs)
        return vectors.map { vector ->
            vector.toList().sortedByDescending { (key, value) -> value }.take(n)
        }
    }
}

