package rejasupotaro.arxiv.reader.spell

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SpellCorrectorTest {
    private val context = InstrumentationRegistry.getTargetContext()
    private val spellCorrector = SpellCorrector(context)

    @Test
    fun run() {
        val actual = spellCorrector.run("camrrot")
        assertThat(actual).isNotEmpty()
    }
}

