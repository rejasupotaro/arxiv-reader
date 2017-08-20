package rejasupotaro.arxiv.reader.data.http

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import rejasupotaro.arxiv.reader.OpenClassOnDebug

@OpenClassOnDebug
class HttpClient {
    private val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()

    fun get(url: String): Response {
        val request = Request.Builder()
                .url(url)
                .build()

        return okHttpClient.newCall(request).execute()
    }
}
