package rejasupotaro.arxiv.reader.api

import android.util.Log
import com.stanfy.gsonxml.GsonXmlBuilder
import com.stanfy.gsonxml.XmlParserCreator
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.xmlpull.v1.XmlPullParserFactory
import rejasupotaro.arxiv.reader.model.ApiResponse
import rejasupotaro.arxiv.reader.model.Paper


class ApiClient {
    val okHttpClient = OkHttpClient.Builder().build()

    val parserCreator = XmlParserCreator {
        try {
            XmlPullParserFactory.newInstance().newPullParser()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    val gson = GsonXmlBuilder()
            .setXmlParserCreator(parserCreator)
            .create()

    fun search(): List<Paper> {
        val response = request()
        return gson.fromXml(response.body()?.string(), ApiResponse::class.java).papers
    }

    fun request(): Response {
        val request = Request.Builder()
                .url("http://export.arxiv.org/api/query?search_query=all:electron")
                .build()
        return okHttpClient.newCall(request).execute()
    }
}
