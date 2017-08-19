package rejasupotaro.arxiv.reader.extensions

import android.icu.text.SimpleDateFormat
import android.text.format.DateUtils
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

fun DateTime.relativeTimeSpanText(): CharSequence {
    return DateUtils.getRelativeTimeSpanString(this.millis)
}

fun DateTime.readableText(): CharSequence {
    val format = DateTimeFormat.forPattern("MMMM d, yyyy")
    return toString(format)
}

