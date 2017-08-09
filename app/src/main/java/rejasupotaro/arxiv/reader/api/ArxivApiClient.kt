package rejasupotaro.arxiv.reader.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request

class ApiClient {
    val okHttpClient = OkHttpClient.Builder().build()

    fun request() {
        object : Thread() {
            override fun run() {
                val request = Request.Builder()
                        .url("http://export.arxiv.org/api/query?search_query=all:electron")
                        .build()
                val response = okHttpClient.newCall(request).execute()
                Log.d("DEBUG", response.body()?.string())
            }
        }.start()
    }
}
