package rejasupotaro.arxiv.reader.api

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import okhttp3.*
import java.io.IOException


class ApiClient {
    private val okHttpClient = OkHttpClient.Builder().build()

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
