package rejasupotaro.arxiv.reader.helper

import org.joda.time.DateTime
import rejasupotaro.arxiv.reader.data.model.Category
import rejasupotaro.arxiv.reader.data.model.Paper
import rejasupotaro.arxiv.reader.data.model.PaperSimilarity
import rejasupotaro.arxiv.reader.data.model.SearchHistory

fun createPaper(
        title: String = "title",
        summary: String = "summary",
        authors: List<String> = listOf("author"),
        categories: List<Category> = listOf(Category.entityToModel("Computer Science - Architecture")),
        downloadUrl: String = "download_url",
        publishedAt: DateTime = DateTime(),
        updatedAt: DateTime = DateTime(),
        downloadedAt: DateTime = DateTime(),
        openedAt: DateTime? = null,
        lastOpenedPage: Int = 1,
        totalPage: Int = 2
): Paper {
    val paper = Paper(
            title = title,
            summary = summary,
            authors = authors,
            categories = categories,
            downloadUrl = downloadUrl,
            publishedAt = publishedAt,
            updatedAt = updatedAt,
            downloadedAt = downloadedAt,
            openedAt = openedAt,
            lastOpenedPage = lastOpenedPage,
            totalPage = totalPage
    )
    paper.id = testDatabase().paperDao().insert(paper)
    return paper
}

fun createSearchHistory(
        query: String = "query",
        createdAt: DateTime = DateTime()
): SearchHistory {
    val searchHistory = SearchHistory(
            query = query,
            createdAt = createdAt
    )
    searchHistory.id = testDatabase().searchHistoryDao().insert(searchHistory)
    return searchHistory
}

fun createPaperSimilarity(
        fromPaper: Paper = createPaper(),
        toPaper: Paper = createPaper(),
        similarity: Double = 0.0
): PaperSimilarity {
    val paperSimilarity = PaperSimilarity(
            fromPaperId = fromPaper.id,
            toPaperId = toPaper.id,
            similarity = similarity
    )
    paperSimilarity.id = testDatabase().paperSimilarityDao().insert(paperSimilarity)
    return paperSimilarity
}