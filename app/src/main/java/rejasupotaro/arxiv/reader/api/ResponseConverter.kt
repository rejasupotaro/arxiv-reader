package rejasupotaro.arxiv.reader.api

import com.stanfy.gsonxml.GsonXmlBuilder
import com.stanfy.gsonxml.XmlParserCreator
import org.xmlpull.v1.XmlPullParserFactory
import rejasupotaro.arxiv.reader.model.ApiResponse
import rejasupotaro.arxiv.reader.model.Paper

object ResponseConverter {
    private val parserCreator = XmlParserCreator {
        try {
            XmlPullParserFactory.newInstance().newPullParser()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    val gson = GsonXmlBuilder()
            .setXmlParserCreator(parserCreator)
            .setSameNameLists(true)
            .create()

    fun xmlToPaper(xml: String): Paper {
        return gson.fromXml(xml, Paper::class.java)
    }

    fun xmlToApiResponse(xml: String): ApiResponse {
        return gson.fromXml(xml, ApiResponse::class.java)
    }
}
