package rejasupotaro.arxiv.reader.extensions

import android.text.Html
import android.text.Spanned

fun String.toHtml(): Spanned {
    return Html.fromHtml(this)
}

