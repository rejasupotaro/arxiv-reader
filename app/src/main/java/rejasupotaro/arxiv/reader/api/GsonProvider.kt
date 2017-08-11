package rejasupotaro.arxiv.reader.api

import com.stanfy.gsonxml.GsonXmlBuilder
import com.stanfy.gsonxml.XmlParserCreator
import org.xmlpull.v1.XmlPullParserFactory

object GsonProvider {
    private val parserCreator = XmlParserCreator {
        try {
            XmlPullParserFactory.newInstance().newPullParser()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    val gson = GsonXmlBuilder()
            .setXmlParserCreator(parserCreator)
            .create()
}
