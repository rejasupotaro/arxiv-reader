package rejasupotaro.arxiv.reader.data

import org.assertj.core.api.Assertions.assertThat
import org.joda.time.DateTime
import org.junit.Test

class GsonProviderTest {
    @Test
    fun serializeDateTime() {
        val expected = DateTime.now()
        val value = gson.toJson(expected, DateTime::class.java)
        gson.fromJson(value, DateTime::class.java).let { actual ->
            assertThat(actual.year).isEqualTo(expected.year)
            assertThat(actual.monthOfYear).isEqualTo(expected.monthOfYear)
            assertThat(actual.dayOfMonth).isEqualTo(expected.dayOfMonth)
            assertThat(actual.hourOfDay).isEqualTo(expected.hourOfDay)
            assertThat(actual.minuteOfHour).isEqualTo(expected.minuteOfHour)
            assertThat(actual.secondOfMinute).isEqualTo(expected.secondOfMinute)
        }
    }
}

