package rejasupotaro.arxiv.reader.data.model

import android.support.test.runner.AndroidJUnit4
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import rejasupotaro.arxiv.reader.data.api.ResponseConverter
import rejasupotaro.arxiv.reader.helper.readTextFromAssets

@RunWith(AndroidJUnit4::class)
class PaperTest {
    @Test
    fun entityToModel() {
        val xml = readTextFromAssets("paper.xml")
        val entity = ResponseConverter.xmlToPaper(xml)
        Paper.entityToModel(entity).apply {
            assertThat(title).isEqualTo("Context-sensitive Spelling Correction Using Google Web 1T 5-Gram Information")
            assertThat(summary).isNotNull()
            assertThat(authors.size).isEqualTo(2)
            assertThat(authors[0]).isEqualTo("Youssef Bassil")
            assertThat(authors[1]).isEqualTo("Mohammad Alwani")
            assertThat(categories.size).isEqualTo(1)
            assertThat(categories[0]).isEqualTo("Computer Science - Computation and Language")
            assertThat(downloadUrl).isEqualTo("http://arxiv.org/pdf/1204.5852v1")
            assertThat(publishedAt).isNotNull()
            assertThat(updatedAt).isNotNull()
        }
    }
}

