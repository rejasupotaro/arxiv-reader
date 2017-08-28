package rejasupotaro.arxiv.reader.data

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.runner.AndroidJUnit4
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import rejasupotaro.arxiv.reader.data.api.ResponseConverter
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.helper.readTextFromAssets

@RunWith(AndroidJUnit4::class)
class FeatureExtractorTest {
    private val context = getInstrumentation().targetContext

    @Test
    fun topNWords() {
        val xml = readTextFromAssets("api_response.xml")
        val apiResponse = ResponseConverter.xmlToApiResponse(xml)
        val papers = apiResponse.papers.map { Paper.entityToModel(it) }
        val vectors = FeatureExtractor.topNWords(context, papers)
        assertThat(vectors[0].size).isEqualTo(10)
        assertThat(vectors).isEqualTo(listOf<String>())
    }

    @Test
    fun stopwords() {
        val stopwords = FeatureExtractor.stopwords(context)
        assertThat(stopwords).isNotEmpty
    }
}
