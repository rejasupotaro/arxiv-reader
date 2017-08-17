package rejasupotaro.arxiv.reader.data.http

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.*
import java.io.IOException

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

