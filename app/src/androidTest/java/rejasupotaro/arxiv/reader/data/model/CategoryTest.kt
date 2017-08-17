package rejasupotaro.arxiv.reader.data.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import rejasupotaro.arxiv.reader.R

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

