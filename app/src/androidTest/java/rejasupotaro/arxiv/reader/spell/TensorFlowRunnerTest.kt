package rejasupotaro.arxiv.reader.spell

import android.support.test.InstrumentationRegistry
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TensorFlowRunnerTest {
    private val runner = TensorFlowRunner(InstrumentationRegistry.getTargetContext().assets)

    @Test
    fun run() {
        val word = "camrrot"
        val normalizedWord = CharacterTable.normalize(word)
        assertThat(normalizedWord).isEqualTo("                             torrmac")

        val vectors = CharacterTable.vectorize(listOf(normalizedWord))
        assertThat(vectors[0][0][CharacterTable.NUM_CLASSES - 1]).isEqualTo(1.0f)

        val input = vectors.flatten()
        val output = runner.run(input)
        assertThat(input.size).isEqualTo(output.size)

        val tensor = Array(1, { Array(CharacterTable.MAX_LEN, { FloatArray(CharacterTable.NUM_CLASSES) }) })
        for (i in 0 until CharacterTable.MAX_LEN) {
            val preds = FloatArray(CharacterTable.NUM_CLASSES)
            for (j in 0 until CharacterTable.NUM_CLASSES) {
                val index = (i * CharacterTable.NUM_CLASSES) + j
                preds[j] = output[index]
            }
            tensor[0][i] = preds
        }
        assertThat(tensor.size).isEqualTo(1)
        assertThat(tensor[0].size).isEqualTo(CharacterTable.MAX_LEN)
        assertThat(tensor[0][0].size).isEqualTo(CharacterTable.NUM_CLASSES)

        val guess = CharacterTable.decode(tensor[0]).trim()
        assertThat(guess).isNotEmpty()
    }

    @Test
    fun inputOperation() {
        val operation = runner.inputOperation
        assertThat(operation.name()).isEqualTo("lstm_1_input")
        assertThat(operation.type()).isEqualTo("Placeholder")
    }

    @Test
    fun outputOperation() {
        val operation = runner.outputOperation
        assertThat(operation.name()).isEqualTo("output_0")
        assertThat(operation.type()).isEqualTo("Identity")
    }
}

