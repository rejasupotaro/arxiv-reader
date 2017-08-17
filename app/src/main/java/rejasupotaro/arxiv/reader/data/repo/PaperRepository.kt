package rejasupotaro.arxiv.reader.data.repo

import rejasupotaro.arxiv.reader.data.api.ResponseConverter
import rejasupotaro.arxiv.reader.data.http.HttpClient
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.ui.paper.search.SearchResponse
import java.io.File

class PaperRepository {
    val httpClient = HttpClient()

    fun search(query: String, page: Int, perPage: Int): SearchResponse {
        val url = "http://export.arxiv.org/api/query?search_query=all:$query&start=${page * perPage}"
        val response = httpClient.get(url)
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
        return result
    }

    fun download(paper: Paper, file: File) {
        val response = httpClient.get(paper.downloadUrl)
        response.body()?.byteStream()?.use {
            it.copyTo(file.outputStream())
        }
    }
}

