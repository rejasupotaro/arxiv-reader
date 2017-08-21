package rejasupotaro.arxiv.reader.ui.paper.search

import android.support.test.runner.AndroidJUnit4
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.junit.Test
import org.junit.runner.RunWith
import rejasupotaro.arxiv.reader.data.repository.PaperRepository
import rejasupotaro.arxiv.reader.data.repository.SearchHistoryRepository
import rejasupotaro.arxiv.reader.data.repository.SearchRequest
import rejasupotaro.arxiv.reader.helper.extensions.waitAndGetValue

@RunWith(AndroidJUnit4::class)
class PaperSearchViewModelTest {
    private val paperRepository = mock<PaperRepository>()
    private val searchHistoryRepository = mock<SearchHistoryRepository>()
    private val viewModel = PaperSearchViewModel(paperRepository, searchHistoryRepository)

    @Test
    fun search() {
        launch(UI) {
            viewModel.searchResults.waitAndGetValue {
                viewModel.query = "query"
            }
            verify(searchHistoryRepository, times(1)).log("query")
            verify(paperRepository, times(1)).search(SearchRequest("query", 1))
        }
    }
}
