package rejasupotaro.arxiv.reader.data

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.runner.AndroidJUnit4
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import rejasupotaro.arxiv.reader.data.api.ResponseConverter
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.helper.readTextFromAssets
import rejasupotaro.arxiv.reader.helper.testDatabase

@RunWith(AndroidJUnit4::class)
class FeatureExtractorTest {
    private val context = getInstrumentation().targetContext
    private val database = testDatabase()
    private val paperDao = database.paperDao()
    private val paperSimilarityDao = database.paperSimilarityDao()

    @Before
    fun setup() {
        paperDao.deleteAll()
        paperSimilarityDao.deleteAll()
    }

    @Test
    fun topNWords() {
        val papers = papers()
        val vectors = FeatureExtractor.topNWords(context, papers)
        vectors.map { vector ->
            assertThat(vector.value.size).isEqualTo(10)
        }
    }

    @Test
    fun stopwords() {
        val stopwords = FeatureExtractor.stopwords(context)
        assertThat(stopwords).isNotEmpty
    }

    @Test
    fun similarPapers() {
        val papers = papers()
        assertThat(papers.size).isEqualTo(10)
        val topNSimilarPapers = FeatureExtractor.topNSimilarPapers(context, papers, 3)
        topNSimilarPapers.forEach { (paperId, similarities) ->
            assertThat(similarities.map { it.first }).doesNotContain(paperId)
            assertThat(similarities.size).isEqualTo(3)
        }
    }

    private fun papers(): List<Paper> {
        val xml = readTextFromAssets("api_response.xml")
        val apiResponse = ResponseConverter.xmlToApiResponse(xml)
        val papers = apiResponse.papers.map { Paper.entityToModel(it) }
        paperDao.insertAll(papers)
        return paperDao.all()
    }
}
