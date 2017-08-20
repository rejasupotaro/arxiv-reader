package rejasupotaro.arxiv.reader.data.db

import android.arch.persistence.room.Room
import android.content.Context
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.runner.AndroidJUnit4
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchHistoryDaoTest {
    val context: Context = getInstrumentation().targetContext
    val db: ArxivDb = Room.databaseBuilder(context, ArxivDb::class.java, "arxiv_test").build()
    val searchHistoryDao = db.searchHistoryDao()

    @Before
    fun setup() {
        searchHistoryDao.deleteAll()
    }

    @Test
    fun latest() {
        val today = DateTime.now()
        val yesterday = DateTime.now().minusDays(1)
        val dayBeforeYesterday = DateTime.now().minusDays(2)

        val searchHistory1 = createSearchHistory(
                query = "query 1",
                createdAt = yesterday
        )
        val searchHistory2 = createSearchHistory(
                query = "query 2",
                createdAt = today
        )
        val searchHistory3 = createSearchHistory(
                query = "query 3",
                createdAt = dayBeforeYesterday
        )

        searchHistoryDao.insert(searchHistory1)
        searchHistoryDao.insert(searchHistory2)
        searchHistoryDao.insert(searchHistory3)

        searchHistoryDao.latest().let { searchHistories ->
            assertThat(searchHistories[0].query).isEqualTo("query 2")
            assertThat(searchHistories[1].query).isEqualTo("query 1")
            assertThat(searchHistories[2].query).isEqualTo("query 3")
        }
    }
}

