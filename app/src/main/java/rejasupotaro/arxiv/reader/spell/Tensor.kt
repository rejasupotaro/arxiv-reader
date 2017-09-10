package rejasupotaro.arxiv.reader.spell

typealias Tensor = Array<Array<FloatArray>>

fun Tensor.flatten(): FloatArray {
    val floatArray = mutableListOf<Float>()
    forEach { it.forEach { it.forEach { floatArray.add(it) } } }
    return floatArray.toFloatArray()
}

fun Tensor.shape(): String {
    val x = size
    val y = get(0).size
    val z = get(0)[0].size
    return "($x, $y, $z)"
}
