package rejasupotaro.arxiv.reader.api

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.runner.AndroidJUnit4
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ResponseConverterTest {

    @Test
    fun xmlToPaper() {
        val xml = readText("paper.xml")
        val paper = ResponseConverter.xmlToPaper(xml)

        paper.apply {
            assertThat(url).isEqualTo("http://arxiv.org/abs/1204.5852v1")
            assertThat(updated).isEqualTo("2012-04-26T07:44:18Z")
            assertThat(published).isEqualTo("2012-04-26T07:44:18Z")
            assertThat(title).isEqualTo("Context-sensitive Spelling Correction Using Google Web 1T 5-Gram Information")
            assertThat(links.size).isEqualTo(2)
        }
    }

    @Test
    fun xmlToPapers() {
        val xml = readText("api_response.xml")
        val apiResponse = ResponseConverter.xmlToApiResponse(xml)

        apiResponse.apply {
            assertThat(id).isEqualTo("http://arxiv.org/api/dGTJJqWuqiHLrncuWvptJXI0CSM")
            assertThat(updated).isEqualTo("2017-08-11T00:00:00-04:00")
            assertThat(totalResults).isEqualTo(550)
            assertThat(startIndex).isEqualTo(0)
            assertThat(itemPerPage).isEqualTo("10")

            link.apply {
                assertThat(href).isEqualTo("http://arxiv.org/api/query?search_query%3Dall%3Aspelling%26id_list%3D%26start%3D0%26max_results%3D10")
                assertThat(rel).isEqualTo("self")
                assertThat(type).isEqualTo("application/atom+xml")
            }

            assertThat(papers.size).isEqualTo(10)
            papers[0].apply {
                assertThat(id).isEqualTo("http://arxiv.org/abs/1204.5852v1")
                assertThat(updated).isEqualTo("2012-04-26T07:44:18Z")
                assertThat(published).isEqualTo("2012-04-26T07:44:18Z")
                assertThat(title).isEqualTo("Context-sensitive Spelling Correction Using Google Web 1T 5-Gram Information")
                assertThat(links.size).isEqualTo(2)
            }
        }
    }

    fun readText(fileName: String): String {
        return getInstrumentation().context.resources.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}

