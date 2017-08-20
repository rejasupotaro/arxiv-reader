package rejasupotaro.arxiv.reader.data.db

import android.support.test.runner.AndroidJUnit4
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.DateTime
import org.junit.Test
import org.junit.runner.RunWith
import rejasupotaro.arxiv.reader.data.model.Category

@RunWith(AndroidJUnit4::class)
class DbConvertersTest {
    @Test
    fun convertStringList() {
        val data = listOf("apple", "banana", "cake")

        val converter = StringListConverter()
        val value = converter.serialize(data)
        val actual = converter.deserialize(value)

        data.mapIndexed { index, item ->
            assertThat(actual[index]).isEqualTo(item)
        }
    }

    @Test
    fun convertDateTime() {
        val data = DateTime.now()

        val converter = DateTimeConverter()
        val value = converter.serialize(data)
        val actual = converter.deserialize(value)

        assertThat(actual?.year).isEqualTo(data.year)
        assertThat(actual?.monthOfYear).isEqualTo(data.monthOfYear)
        assertThat(actual?.dayOfMonth).isEqualTo(data.dayOfMonth)
        assertThat(actual?.hourOfDay).isEqualTo(data.hourOfDay)
        assertThat(actual?.minuteOfHour).isEqualTo(data.minuteOfHour)
        assertThat(actual?.secondOfMinute).isEqualTo(data.secondOfMinute)
    }

    @Test
    fun convertCategoryList() {
        val data = listOf(
                Category.entityToModel("Computer Science - Machine Learning"),
                Category.entityToModel("Computer Science - Databases")
        )

        val converter = CategoryConverter()
        val value = converter.serialize(data)
        val actual = converter.deserialize(value)

        data.mapIndexed { index, (primary, sub, color) ->
            assertThat(actual[index].primary).isEqualTo(primary)
            assertThat(actual[index].sub).isEqualTo(sub)
            assertThat(actual[index].color).isEqualTo(color)
        }
    }
}
