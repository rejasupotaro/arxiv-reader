package rejasupotaro.arxiv.reader.spell

import android.support.test.runner.AndroidJUnit4
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterTableTest {
    @Test
    fun charToIndex() {
        var actual = CharacterTable.CHAR_INDICES['a']
        assertThat(actual).isEqualTo(0)

        actual = CharacterTable.CHAR_INDICES[' ']
        assertThat(actual).isEqualTo(26)
    }

    @Test
    fun indexToChar() {
        var actual = CharacterTable.INDEX_CHARS[0]
        assertThat(actual).isEqualTo('a')

        actual = CharacterTable.INDEX_CHARS[26]
        assertThat(actual).isEqualTo(' ')
    }

    @Test
    fun encode() {
        val actual = CharacterTable.encode("word")
        assertThat(actual.size).isEqualTo(CharacterTable.MAX_LEN)
    }

    @Test
    fun decode() {
        val vector = arrayOf(
                floatArrayOf(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f),
                floatArrayOf(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f),
                floatArrayOf(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f)
        )
        val actual = CharacterTable.decode(vector)
        assertThat(actual).isEqualTo("abc")
    }

    @Test
    fun vectorize() {
        val actual = CharacterTable.vectorize(listOf("a"))
        assertThat(actual.shape()).isEqualTo("(1, 36, 27)")
        assertThat(actual[0][0][0]).isEqualTo(1.0f)
        assertThat(actual.flatten().size).isEqualTo(972)
    }

    @Test
    fun normalize() {
        val actual = CharacterTable.normalize("word")
        assertThat(actual).isEqualTo("                                drow")
    }
}

