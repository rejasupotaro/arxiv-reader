package rejasupotaro.arxiv.reader.spell

import android.content.Context
import android.util.Log

class SpellCorrector(context: Context) {
    private val runner = TensorFlowRunner(context.assets)

    fun run(word: String): String {
        val normalizedWord = CharacterTable.normalize(word)
        val vectors = CharacterTable.vectorize(listOf(normalizedWord))
        val input = vectors.flatten()
        val output = runner.run(input)
        val tensor = Array(1, { Array(CharacterTable.MAX_LEN, { FloatArray(CharacterTable.NUM_CLASSES) }) })
        for (i in 0 until CharacterTable.MAX_LEN) {
            for (j in 0 until CharacterTable.NUM_CLASSES) {
                val index = (i * CharacterTable.NUM_CLASSES) + j
                val p = output[index]
                tensor[0][i][j] = p
            }
        }
        val guess = CharacterTable.decode(tensor[0]).trim()
        Log.e("SpellCorrector", "${CharacterTable.decode(vectors[0])} => ${CharacterTable.encode(word)} => $guess")
        return guess
    }
}
