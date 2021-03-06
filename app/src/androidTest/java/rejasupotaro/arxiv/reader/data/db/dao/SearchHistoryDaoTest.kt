package rejasupotaro.arxiv.reader.data.db.dao

import android.support.test.runner.AndroidJUnit4
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import rejasupotaro.arxiv.reader.helper.createSearchHistory
import rejasupotaro.arxiv.reader.helper.testDatabase

@RunWith(AndroidJUnit4::class)
class SearchHistoryDaoTest {
    private val searchHistoryDao = testDatabase().searchHistoryDao()

    @Before
    fun setup() {
        searchHistoryDao.deleteAll()
    }

    @Test
    fun latest() {
        val today = DateTime.now()
        val yesterday = DateTime.now().minusDays(1)
        val dayBeforeYesterday = DateTime.now().minusDays(2)

        createSearchHistory(
                query = "query 1",
                createdAt = yesterday
        )
        createSearchHistory(
                query = "query 2",
                createdAt = today
        )
        createSearchHistory(
                query = "query 3",
                createdAt = dayBeforeYesterday
        )

        searchHistoryDao.latest().let { searchHistories ->
            assertThat(searchHistories[0].query).isEqualTo("query 2")
            assertThat(searchHistories[1].query).isEqualTo("query 1")
            assertThat(searchHistories[2].query).isEqualTo("query 3")
        }
    }
}

