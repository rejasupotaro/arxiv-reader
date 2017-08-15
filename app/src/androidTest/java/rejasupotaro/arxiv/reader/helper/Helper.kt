package rejasupotaro.arxiv.reader.helper

import android.support.test.InstrumentationRegistry.getInstrumentation

fun readTextFromAssets(fileName: String): String {
    val assetManager = getInstrumentation().context.resources.assets
    return assetManager.open(fileName).bufferedReader().use { it.readText() }
}

