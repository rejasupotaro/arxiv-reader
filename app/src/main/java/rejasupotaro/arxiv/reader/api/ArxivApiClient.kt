package rejasupotaro.arxiv.reader.api

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.stanfy.gsonxml.GsonXmlBuilder
import com.stanfy.gsonxml.XmlParserCreator
import okhttp3.*
import org.xmlpull.v1.XmlPullParserFactory
import rejasupotaro.arxiv.reader.model.ApiResponse
import rejasupotaro.arxiv.reader.model.Paper
import java.io.IOException


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

    fun search(): LiveData<List<Paper>> {
        return request().map { body ->
            gson.fromXml(body, ApiResponse::class.java).papers
        }
    }

    fun request(): LiveData<String> {
        val liveData = MutableLiveData<String>()

        val request = Request.Builder()
                .url("http://export.arxiv.org/api/query?search_query=all:electron")
                .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    throw IOException("Unexpected code " + response)
                }
                liveData.postValue(response.body()?.string())
            }
        })

        return liveData
    }
}

fun <X, Y> LiveData<X>.map(func: (X) -> Y): LiveData<Y> {
    return Transformations.map(this) {
        func.invoke(it)
    }
}