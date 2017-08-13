package rejasupotaro.arxiv.reader.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import rejasupotaro.arxiv.reader.api.ResponseConverter
import rejasupotaro.arxiv.reader.http.HttpClient
import rejasupotaro.arxiv.reader.model.Paper

class PaperRepository {
    val httpClient = HttpClient()

    fun search(query: String): LiveData<List<Paper>> {
        val papers = MutableLiveData<List<Paper>>()
        val url = "http://export.arxiv.org/api/query?search_query=all:$query"
        httpClient.get(url, { response ->
            papers.postValue(ResponseConverter
                    .xmlToApiResponse(response.body().toString())
                    .papers
                    .map { Paper.entityToModel(it) })
        })
        return papers
    }
}

