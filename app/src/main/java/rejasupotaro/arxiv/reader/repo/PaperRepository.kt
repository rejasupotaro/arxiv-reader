package rejasupotaro.arxiv.reader.repo

import android.arch.lifecycle.LiveData
import rejasupotaro.arxiv.reader.api.ApiClient
import rejasupotaro.arxiv.reader.api.GsonProvider
import rejasupotaro.arxiv.reader.extensions.map
import rejasupotaro.arxiv.reader.model.ApiResponse
import rejasupotaro.arxiv.reader.model.Paper

class PaperRepository {
    val apiClient = ApiClient()

    fun search(query: String): LiveData<List<Paper>> {
        val url = "http://export.arxiv.org/api/query?search_query=all:$query"
        return apiClient.request(url).map { body ->
            GsonProvider.gson.fromXml(body, ApiResponse::class.java).papers
        }
    }
}

