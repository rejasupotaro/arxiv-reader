package rejasupotaro.arxiv.reader.ui.paper.read

import android.arch.lifecycle.MutableLiveData
import android.support.test.runner.AndroidJUnit4
import com.nhaarman.mockito_kotlin.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.repository.PaperRepository
import rejasupotaro.arxiv.reader.helper.createPaper
import rejasupotaro.arxiv.reader.helper.extensions.waitAndGetValue

@RunWith(AndroidJUnit4::class)
class PaperReadViewModelTest {
    private val paperRepository = mock<PaperRepository>()
    private val viewModel = PaperReadViewModel(paperRepository)

    @Test
    fun open() {
        launch(UI) {
            val data = MutableLiveData<Paper>()
            data.value = createPaper()
            whenever(paperRepository.findById(1)).thenReturn(data)

            viewModel.paper.waitAndGetValue {
                viewModel.paperId.value = 1
            }

            val argumentCaptor = argumentCaptor<Paper>()
            verify(paperRepository, times(1)).update(argumentCaptor.capture())

            val actual = argumentCaptor.firstValue
            assertThat(actual.openedAt).isNotNull()
        }
    }
}

