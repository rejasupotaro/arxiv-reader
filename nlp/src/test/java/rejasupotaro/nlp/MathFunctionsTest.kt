package rejasupotaro.nlp

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class MathFunctionsTest {
    @Test
    fun testCosineSimilarity() {
        run {
            val vector1 = doubleArrayOf(0.0, 1.0)
            val vector2 = doubleArrayOf(1.0, 0.0)
            val similarity = cosineSimilarity(vector1, vector2)
            assertThat(similarity).isEqualTo(0.0)
        }

        run {
            val vector1 = doubleArrayOf(1.0, 0.0)
            val vector2 = doubleArrayOf(1.0, 0.0)
            val similarity = cosineSimilarity(vector1, vector2)
            assertThat(similarity).isEqualTo(1.0)
        }
    }
}