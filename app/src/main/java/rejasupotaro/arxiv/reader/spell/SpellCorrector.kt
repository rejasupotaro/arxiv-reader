package rejasupotaro.arxiv.reader.spell

import android.content.Context

class SpellCorrector(context: Context) {
    private val runner = TensorFlowRunner(context.assets)

    fun run(word: String): String {
        val input = wordToInput(word)
        val output = runner.run(input)
        val tensor = outputToTensor(output)
        return CharacterTable.decode(tensor[0]).trim()
    }

    private fun wordToInput(word: String): FloatArray {
        val normalizedWord = CharacterTable.normalize(word)
        val vectors = CharacterTable.vectorize(listOf(normalizedWord))
        return vectors.flatten()
    }

    private fun outputToTensor(output: FloatArray): Tensor {
        val tensor = Array(1, { Array(CharacterTable.MAX_LEN, { FloatArray(CharacterTable.NUM_CLASSES) }) })
        for (i in 0 until CharacterTable.MAX_LEN) {
            val preds = FloatArray(CharacterTable.NUM_CLASSES)
            for (j in 0 until CharacterTable.NUM_CLASSES) {
                val index = (i * CharacterTable.NUM_CLASSES) + j
                preds[j] = output[index]
            }
            tensor[0][i] = preds
        }
        return tensor
    }
}
