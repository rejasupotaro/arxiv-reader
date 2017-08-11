package rejasupotaro.arxiv.reader.repo

import android.arch.lifecycle.LiveData
import rejasupotaro.arxiv.reader.api.ApiClient
import rejasupotaro.arxiv.reader.api.GsonProvider
import rejasupotaro.arxiv.reader.extensions.map
import rejasupotaro.arxiv.reader.model.ApiResponse
import rejasupotaro.arxiv.reader.model.Paper

class PaperRepository {
    val apiClient = ApiClient()

    fun search(): LiveData<List<Paper>> {
        return apiClient.request().map { body ->
            GsonProvider.gson.fromXml(body, ApiResponse::class.java).papers
        }
    }
}

