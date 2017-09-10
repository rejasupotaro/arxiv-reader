package rejasupotaro.arxiv.reader.spell

object CharacterTable {
    val MAX_LEN = 36
    val CHARS = "abcdefghijklmnopqrstuvwxyz ".toCharArray()
    val NUM_CLASSES = CHARS.size
    val CHAR_INDICES = CHARS.mapIndexed { index, char -> Pair(index, char) }.associateBy({ it.second }, { it.first })
    val INDEX_CHARS = CHARS.mapIndexed { index, char -> Pair(index, char) }.associateBy({ it.first }, { it.second })

    fun encode(word: String): Array<FloatArray> {
        val vector = Array(MAX_LEN, { FloatArray(NUM_CLASSES) })
        word.toCharArray().forEachIndexed { index, char ->
            CHAR_INDICES[char]?.let {
                vector[index][it] = 1F
            }
        }
        return vector
    }

    fun decode(vector: Array<FloatArray>): String {
        return vector.map { preds ->
            val index = preds.max()?.let {
                preds.indexOf(it)
            }
            CharacterTable.INDEX_CHARS[index]
        }.joinToString("")
    }

    fun vectorize(words: List<String>): Tensor {
        val tensor = words.map { word ->
            encode(word)
        }
        return tensor.toTypedArray()
    }

    fun normalize(word: String) = fillWithWhitespace(word).reversed()

    fun fillWithWhitespace(word: String) = word.padEnd(MAX_LEN, ' ')
}
