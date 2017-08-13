package rejasupotaro.arxiv.reader.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import rejasupotaro.arxiv.reader.api.ResponseConverter
import rejasupotaro.arxiv.reader.http.HttpClient
import rejasupotaro.arxiv.reader.model.Paper
import java.io.File


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

    fun download(paper: Paper, file: File) {
        httpClient.get(paper.downloadUrl, { response ->
            response.body()?.byteStream()?.use {
                it.copyTo(file.outputStream())
            }
        })
    }
}

