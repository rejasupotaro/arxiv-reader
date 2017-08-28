package rejasupotaro.nlp

fun String.tokenize(): List<String> {
    return toLowerCase()
            .split(" ")
            .map(String::removeSpecialCharacters)
}

fun String.removeSpecialCharacters(): String {
    return "[^a-zA-Z]".toRegex().replace(this, "")
}

