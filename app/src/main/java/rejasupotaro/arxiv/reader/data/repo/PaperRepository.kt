package rejasupotaro.arxiv.reader.data.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import rejasupotaro.arxiv.reader.data.api.ResponseConverter
import rejasupotaro.arxiv.reader.data.http.HttpClient
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.ui.paper.search.SearchResponse
import java.io.File

class PaperRepository {
    val httpClient = HttpClient()

    fun search(query: String, page: Int, perPage: Int): LiveData<SearchResponse> {
        val papers = MutableLiveData<SearchResponse>()
        val url = "http://export.arxiv.org/api/query?search_query=all:$query&start=${page * perPage}"
        httpClient.get(url, { response ->
            val body = response.body()?.string() ?: ""
            val result = ResponseConverter
                    .xmlToApiResponse(body)
                    .let {
                        SearchResponse(
                                query,
                                it.papers.map { Paper.entityToModel(it) },
                                page,
                                it.totalResults.content
                        )
                    }
            papers.postValue(result)
        })
        return papers
    }

    fun download(paper: Paper, file: File): LiveData<Unit> {
        val signal = MutableLiveData<Unit>()
        httpClient.get(paper.downloadUrl, { response ->
            response.body()?.byteStream()?.use {
                it.copyTo(file.outputStream())
                signal.postValue(Unit)
            }
        })
        return signal
    }
}

