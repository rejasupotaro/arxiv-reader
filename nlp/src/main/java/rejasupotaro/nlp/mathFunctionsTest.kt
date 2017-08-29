package rejasupotaro.nlp

fun cosineSimilarity(vector1: DoubleArray, vector2: DoubleArray): Double {
    var dotProduct = 0.0
    var magnitude1 = 0.0
    var magnitude2 = 0.0

    (vector1.indices).forEach { i ->
        dotProduct += vector1[i] * vector2[i]
        magnitude1 += Math.pow(vector1[i], 2.0)
        magnitude2 += Math.pow(vector2[i], 2.0)
    }

    magnitude1 = Math.sqrt(magnitude1)
    magnitude2 = Math.sqrt(magnitude2)

    return if ((magnitude1 != 0.0) or (magnitude2 != 0.0)) {
        dotProduct / (magnitude1 * magnitude2)
    } else {
        0.0
    }
}

