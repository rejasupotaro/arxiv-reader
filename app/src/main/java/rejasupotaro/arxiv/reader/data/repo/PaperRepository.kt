package rejasupotaro.arxiv.reader.data.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import rejasupotaro.arxiv.reader.data.api.ResponseConverter
import rejasupotaro.arxiv.reader.data.http.HttpClient
import rejasupotaro.arxiv.reader.data.model.Paper
import java.io.File


class PaperRepository {
    val httpClient = HttpClient()

    fun search(query: String): LiveData<List<Paper>> {
        val papers = MutableLiveData<List<Paper>>()
        val url = "http://export.arxiv.org/api/query?search_query=all:$query"
        httpClient.get(url, { response ->
            val body = response.body()?.string() ?: ""
            val result = ResponseConverter
                    .xmlToApiResponse(body)
                    .papers
                    .map { Paper.entityToModel(it) }
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

