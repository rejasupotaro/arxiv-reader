package rejasupotaro.arxiv.reader.data.repo

import android.arch.lifecycle.LiveData
import org.joda.time.DateTime
import rejasupotaro.arxiv.reader.data.api.ResponseConverter
import rejasupotaro.arxiv.reader.data.db.ArxivDb
import rejasupotaro.arxiv.reader.data.http.HttpClient
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.extensions.map
import rejasupotaro.arxiv.reader.extensions.observable
import java.io.File

class PaperRepository(private val db: ArxivDb, private val httpClient: HttpClient) {
    fun list(): LiveData<List<Paper>> {
        return observable {
            db.paperDao().findAll()
        }
    }

    fun delete(paper: Paper): LiveData<Unit> {
        return observable {
            db.paperDao().delete(paper)
        }
    }

    fun findById(paperId: Long): LiveData<Paper> {
        return observable {
            db.paperDao().findById(paperId)
        }
    }

    fun openById(paperId: Long): LiveData<Paper> {
        return findById(paperId).map { paper ->
            paper.openedAt = DateTime.now()
            update(paper)
            paper
        }
    }

    fun update(paper: Paper): LiveData<Paper> {
        return observable {
            db.paperDao().update(paper)
            paper
        }
    }

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

data class SearchRequest(val query: String, val page: Int, val perPage: Int = 20)
data class SearchResponse(val query: String, val result: List<Paper>, val page: Int, val totalPages: Int)

