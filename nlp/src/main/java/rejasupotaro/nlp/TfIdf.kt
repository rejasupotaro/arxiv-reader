package rejasupotaro.nlp

typealias Word = String
typealias Doc = List<Word>
typealias Docs = List<Doc>

object TfIdf {
    fun vectorize(docs: Docs, stopwords: List<String> = listOf()): List<Map<String, Double>> {
        val terms = docs.toMutableSet().flatten().filterNot { stopwords.contains(it) }.distinct()
        return docs.map { doc ->
            val vector = mutableMapOf<String, Double>()
            terms.filterNot { stopwords.contains(it) }.forEach { term ->
                vector[term] = tfidf(docs, doc, term)
            }
            vector
        }
    }

    fun tfidf(docs: Docs, doc: Doc, term: Word): Double {
        val tf = tf(doc, term)
        val idf = idf(docs, term)
        return tf * idf
    }

    fun tf(doc: Doc, term: String): Double {
        var n = 0
        doc.forEach { word ->
            if (term.equals(word, ignoreCase = true)) {
                n++
            }
        }
        return n / doc.size.toDouble()
    }

    fun idf(docs: Docs, term: Word): Double {
        var n = 0
        docs.map { doc ->
            if (doc.contains(term)) {
                n++
            }
        }
        return Math.log(docs.size / n.toDouble())
    }
}
