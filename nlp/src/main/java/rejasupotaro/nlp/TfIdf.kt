package rejasupotaro.nlp

object TfIdf {
    fun calculate(docs: Collection<List<String>>, doc: List<String>, term: String): Double {
        val tf = tf(doc, term)
        val idf = idf(docs, term)
        return tf * idf
    }

    fun tf(doc: List<String>, term: String): Double {
        println("$doc / $term")
        var n = 0
        doc.forEach { word ->
            if (term.equals(word, ignoreCase = true)) {
                n++
            }
        }
        return n / doc.size.toDouble()
    }

    fun idf(docs: Collection<List<String>>, term: String): Double {
        var n = 0
        docs.map { doc ->
            if (doc.contains(term)) {
                n++
            }
        }
        return Math.log(docs.size / n.toDouble())
    }
}

