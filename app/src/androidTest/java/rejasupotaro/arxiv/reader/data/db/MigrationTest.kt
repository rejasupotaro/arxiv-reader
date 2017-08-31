package rejasupotaro.arxiv.reader.data.db

import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory
import android.arch.persistence.room.testing.MigrationTestHelper
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import rejasupotaro.arxiv.reader.helper.TEST_DB_NAME

@RunWith(AndroidJUnit4::class)
class MigrationTest {
    @Rule
    @JvmField
    val helper = MigrationTestHelper(
            InstrumentationRegistry.getInstrumentation(),
            ArxivDb::class.java.canonicalName,
            FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    fun migrate1To2() {
        val db = helper.createDatabase(TEST_DB_NAME, 1)
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS `papers` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `title` TEXT NOT NULL,
                `summary` TEXT NOT NULL,
                `authors` TEXT NOT NULL,
                `categories` TEXT NOT NULL,
                `download_url` TEXT NOT NULL,
                `published_at` TEXT NOT NULL,
                `updated_at` TEXT NOT NULL,
                `downloaded_at` TEXT NOT NULL,
                `opened_at` TEXT,
                `last_opened_page` INTEGER NOT NULL,
                `total_page` INTEGER NOT NULL
            )
            """)
        db.close()
        helper.runMigrationsAndValidate(TEST_DB_NAME, 2, true, AddPaperSimilaritiesTable())
    }
}

