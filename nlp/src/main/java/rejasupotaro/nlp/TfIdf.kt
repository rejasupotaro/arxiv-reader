package rejasupotaro.nlp

object TfIdf {
    fun vectorize(docs: List<List<String>>, stopwords: List<String> = listOf<String>()): List<Map<String, Double>> {
        val terms = docs.toMutableSet().flatten().filterNot { stopwords.contains(it) }.distinct()
        return docs.map { doc ->
            val vector = mutableMapOf<String, Double>()
            terms.filterNot { stopwords.contains(it) }.forEach { term ->
                vector[term] = tfidf(docs, doc, term)
            }
            vector
        }
    }

    fun tfidf(docs: List<List<String>>, doc: List<String>, term: String): Double {
        val tf = tf(doc, term)
        val idf = idf(docs, term)
        return tf * idf
    }

    fun tf(doc: List<String>, term: String): Double {
        var n = 0
        doc.forEach { word ->
            if (term.equals(word, ignoreCase = true)) {
                n++
            }
        }
        return n / doc.size.toDouble()
    }

    fun idf(docs: List<List<String>>, term: String): Double {
        var n = 0
        docs.map { doc ->
            if (doc.contains(term)) {
                n++
            }
        }
        return Math.log(docs.size / n.toDouble())
    }
}
