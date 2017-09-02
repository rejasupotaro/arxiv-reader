package rejasupotaro.arxiv.reader.ui.paper.view

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.junit.Test
import org.junit.runner.RunWith
import rejasupotaro.arxiv.reader.data.repository.PaperRepository
import rejasupotaro.arxiv.reader.helper.extensions.waitAndGetValue

@RunWith(AndroidJUnit4::class)
class PaperSearchViewModelTest {
    private val context = InstrumentationRegistry.getContext()
    private val paperRepository = mock<PaperRepository>()
    private val viewModel = PaperViewViewModel(context, paperRepository)

    @Test
    fun search() {
        launch(UI) {
            viewModel.paper.waitAndGetValue {
                viewModel.loadPaper("title")
            }
            verify(paperRepository, times(1)).findByTitle("title")
        }
    }
}

