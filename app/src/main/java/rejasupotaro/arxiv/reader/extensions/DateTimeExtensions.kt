package rejasupotaro.arxiv.reader.extensions

import android.text.format.DateUtils
import org.joda.time.DateTime

fun DateTime.relativeTimeSpanText(): CharSequence {
    return DateUtils.getRelativeTimeSpanString(this.millis)
}

