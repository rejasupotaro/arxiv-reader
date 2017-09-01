package rejasupotaro.arxiv.reader.data.db.dao

import android.support.test.runner.AndroidJUnit4
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import rejasupotaro.arxiv.reader.helper.createPaper
import rejasupotaro.arxiv.reader.helper.createPaperSimilarity
import rejasupotaro.arxiv.reader.helper.testDatabase

@RunWith(AndroidJUnit4::class)
class PaperSimilarityDaoTest {
    private val paperDao = testDatabase().paperDao()
    private val paperSimilarityDao = testDatabase().paperSimilarityDao()

    @Before
    fun setup() {
        paperDao.deleteAll()
        paperSimilarityDao.deleteAll()
    }

    @Test
    fun insertAndDelete() {
        val paper1 = createPaper(title = "Paper 1")
        val paper2 = createPaper(title = "Paper 2")

        paperSimilarityDao.findByFromPaperId(paper1.id).let { paperSimilarities ->
            assertThat(paperSimilarities).isEmpty()
        }

        createPaperSimilarity(paper1, paper2, 0.9)

        paperSimilarityDao.findByFromPaperId(paper1.id).let { paperSimilarities ->
            assertThat(paperSimilarities).hasSize(1)
            assertThat(paperSimilarities[0].fromPaperId).isEqualTo(paper1.id)
            assertThat(paperSimilarities[0].toPaperId).isEqualTo(paper2.id)
            assertThat(paperSimilarities[0].similarity).isEqualTo(0.9)
        }

        paperDao.delete(paper1)

        paperSimilarityDao.findByFromPaperId(paper1.id).let { paperSimilarities ->
            assertThat(paperSimilarities).isEmpty()
        }
    }
}

