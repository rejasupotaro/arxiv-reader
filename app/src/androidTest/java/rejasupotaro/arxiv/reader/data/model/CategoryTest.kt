package rejasupotaro.arxiv.reader.data.model

import android.support.test.runner.AndroidJUnit4
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import rejasupotaro.arxiv.reader.R

@RunWith(AndroidJUnit4::class)
class CategoryTest {
    @Test
    fun entityToModel() {
        Category.entityToModel("Computer Science").apply {
            assertThat(primary).isEqualTo("Computer Science")
            assertThat(sub).isEmpty()
            assertThat(color).isEqualTo(R.color.ink_orange)
        }

        Category.entityToModel("Computer Science - Network").apply {
            assertThat(primary).isEqualTo("Computer Science")
            assertThat(sub).isEqualTo("Network")
            assertThat(color).isEqualTo(R.color.ink_orange)
        }
    }
}

