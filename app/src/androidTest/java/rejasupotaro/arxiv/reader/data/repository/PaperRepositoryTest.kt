package rejasupotaro.arxiv.reader.data.repository

import android.support.test.runner.AndroidJUnit4
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import rejasupotaro.arxiv.reader.data.http.HttpClient
import rejasupotaro.arxiv.reader.helper.testDatabase

@RunWith(AndroidJUnit4::class)
class PaperRepositoryTest {
    private val db = testDatabase()
    private val httpClient = mock<HttpClient>()

    @Test
    fun search() {
        val argumentCaptor = argumentCaptor<String>()
        whenever(httpClient.get(argumentCaptor.capture())).thenReturn(null)

        val repository = PaperRepository(db, httpClient)
        val searchRequest = SearchRequest("query", 1, 20)
        repository.search(searchRequest)

        assertThat(argumentCaptor.firstValue).isEqualTo("http://export.arxiv.org/api/query?search_query=all:query&start=20")
    }
}

