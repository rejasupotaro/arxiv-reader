package rejasupotaro.nlp

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class StringExtensionsTest {
    @Test
    fun tokenize() {
        val tokens = "apple orange1 orange2 banana".tokenize()
        assertThat(tokens).isEqualTo(listOf("apple", "orange", "orange", "banana"))
    }

    @Test
    fun removeNumbers() {
        val text = "jdgh&jk8^i0ssh6".removeSpecialCharacters()
        assertThat(text).isEqualTo("jdghjkissh")
    }
}
