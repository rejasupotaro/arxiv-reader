package rejasupotaro.arxiv.reader.helper

import android.arch.persistence.room.Room
import android.content.Context
import android.support.test.InstrumentationRegistry.getInstrumentation
import rejasupotaro.arxiv.reader.data.db.ArxivDb

fun readTextFromAssets(fileName: String): String {
    val assetManager = getInstrumentation().context.resources.assets
    return assetManager.open(fileName).bufferedReader().use { it.readText() }
}

val TEST_DB_NAME = "arxiv_test"

fun testDatabase(): ArxivDb {
    val context: Context = getInstrumentation().targetContext
    return Room.databaseBuilder(context, ArxivDb::class.java, TEST_DB_NAME).build()
}

