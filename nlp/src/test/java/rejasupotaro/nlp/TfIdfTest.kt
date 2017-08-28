package rejasupotaro.nlp

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TfIdfTest {
    @Test
    fun tf() {
        val doc = "apple orange orange banana".split(" ").map { it.toLowerCase() }

        TfIdf.tf(doc, "orange").let {
            assertThat(it).isEqualTo(0.5)
        }

        TfIdf.tf(doc, "banana").let {
            assertThat(it).isEqualTo(0.25)
        }
    }

    @Test
    fun idf() {
        val doc1 = "apple orange orange banana".split(" ").map { it.toLowerCase() }
        val doc2 = "apple banana banana kiwi".split(" ").map { it.toLowerCase() }
        val docs = listOf(doc1, doc2)

        TfIdf.idf(docs, "apple").let {
            assertThat(it).isEqualTo(0.0)
        }

        TfIdf.idf(docs, "banana").let {
            assertThat(it).isEqualTo(0.0)
        }
    }

    @Test
    fun tfidf() {
        val doc1 = "apple orange orange banana".split(" ").map { it.toLowerCase() }
        val doc2 = "apple banana banana kiwi".split(" ").map { it.toLowerCase() }
        val docs = listOf(doc1, doc2)

        val appleInDoc1 = TfIdf.tfidf(docs, doc1, "apple")
        assertThat(appleInDoc1).isEqualTo(0.0)

        val bananaInDoc2 = TfIdf.tfidf(docs, doc2, "banana")
        assertThat(bananaInDoc2).isEqualTo(0.0)

        val orangeInDoc1 = TfIdf.tfidf(docs, doc1, "orange")
        val kiwiInDoc2 = TfIdf.tfidf(docs, doc2, "kiwi")
        assertThat(orangeInDoc1 > kiwiInDoc2).isTrue()
    }

    @Test
    fun vectorize() {
        val doc1 = "apple orange orange banana".split(" ").map { it.toLowerCase() }
        val doc2 = "apple banana banana kiwi".split(" ").map { it.toLowerCase() }
        val docs = listOf(doc1, doc2)

        val vectors = TfIdf.vectorize(docs)

        val appleInDoc1 = TfIdf.tfidf(docs, doc1, "apple")
        assertThat(appleInDoc1).isEqualTo(vectors[0]["apple"])

        val orangeInDoc1 = TfIdf.tfidf(docs, doc1, "orange")
        assertThat(orangeInDoc1).isEqualTo(vectors[0]["orange"])
    }
}

