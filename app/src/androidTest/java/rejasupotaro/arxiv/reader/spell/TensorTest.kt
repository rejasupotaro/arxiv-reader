package rejasupotaro.arxiv.reader.spell

import android.support.test.runner.AndroidJUnit4
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TensorTest {
    @Test
    fun flatten() {
        var tensor = Array(3, { Array(3, { FloatArray(3) }) })
        assertThat(tensor.shape()).isEqualTo("(3, 3, 3)")
        assertThat(tensor.flatten()).isEqualTo(FloatArray(27))

        tensor = Array(1, { Array(1, { FloatArray(1) }) })
        assertThat(tensor.shape()).isEqualTo("(1, 1, 1)")
        assertThat(tensor.flatten()).isEqualTo(floatArrayOf(0F))
        tensor[0][0][0] = 1F
        assertThat(tensor.flatten()).isEqualTo(floatArrayOf(1F))

        tensor = Array(1, { Array(2, { FloatArray(3) }) })
        assertThat(tensor.shape()).isEqualTo("(1, 2, 3)")
        tensor[0][1][2] = 1F
        assertThat(tensor.flatten()).isEqualTo(floatArrayOf(
                0F, 0F, 0F, 0F, 0F, 1F
        ))
    }
}
