package rejasupotaro.arxiv.reader.data.db

import android.support.test.runner.AndroidJUnit4
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import rejasupotaro.arxiv.reader.helper.testDatabase

@RunWith(AndroidJUnit4::class)
class PaperDaoTest {
    private val paperDao = testDatabase().paperDao()

    @Before
    fun setup() {
        paperDao.deleteAll()
    }

    @Test
    fun insertAndUpdateAndDelete() {
        val paper = createPaper(title = "BIRRA MORETTI")
        paper.id = paperDao.insert(paper)

        paperDao.findById(paper.id).let {
            assertThat(it.title).isEqualTo("BIRRA MORETTI")
        }

        paper.title = "BIRRA MORETTI (NEW)"
        paperDao.update(paper)

        paperDao.findById(paper.id).let {
            assertThat(it.title).isEqualTo("BIRRA MORETTI (NEW)")
        }

        paperDao.delete(paper)
        paperDao.findById(paper.id).let {
            assertThat(it).isNull()
        }
    }

    @Test
    fun findAll() {
        val today = DateTime.now()
        val yesterday = DateTime.now().minusDays(1)
        val dayBeforeYesterday = DateTime.now().minusDays(2)

        val paper1 = createPaper(
                title = "Expected to be 1",
                downloadedAt = dayBeforeYesterday,
                openedAt = today
        )
        val paper2 = createPaper(
                title = "Expected to be 2",
                downloadedAt = today,
                openedAt = null
        )
        val paper3 = createPaper(
                title = "Expected to be 3",
                downloadedAt = yesterday,
                openedAt = yesterday
        )

        paperDao.insert(paper1)
        paperDao.insert(paper2)
        paperDao.insert(paper3)

        paperDao.findAll().let { papers ->
            assertThat(papers.size).isEqualTo(3)
            assertThat(papers[0].title).isEqualTo("Expected to be 1")
            assertThat(papers[1].title).isEqualTo("Expected to be 2")
            assertThat(papers[2].title).isEqualTo("Expected to be 3")
        }
    }
}

