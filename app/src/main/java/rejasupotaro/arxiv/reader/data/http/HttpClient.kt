package rejasupotaro.arxiv.reader.data.http

import okhttp3.*
import java.io.IOException

class HttpClient {
    private val okHttpClient = OkHttpClient.Builder().build()

    fun get(url: String,
            handler: (Response) -> Unit,
            errorHandler: (IOException) -> Unit = { _ -> }
    ) {
        val request = Request.Builder()
                .url(url)
                .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                errorHandler.invoke(e)
            }

            override fun onResponse(call: Call, response: Response) {
                handler.invoke(response)
            }
        })
    }
}

