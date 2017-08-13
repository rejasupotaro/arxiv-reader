package rejasupotaro.arxiv.reader.data.api

import com.google.gson.annotations.SerializedName

data class ApiResponse(
        @SerializedName("id") val id: String,
        @SerializedName("updated") val updated: String,
        @SerializedName("entry") val papers: List<PaperEntity>,
        @SerializedName("link") val link: LinkEntity,
        @SerializedName("opensearch:totalResults") val totalResults: TotalResults,
        @SerializedName("opensearch:startIndex") val startIndex: StartIndex,
        @SerializedName("opensearch:itemsPerPage") val itemPerPage: ItemPerPage
)

data class TotalResults(
        @SerializedName("content") val content: Int
)

data class StartIndex(
        @SerializedName("content") val content: Int
)

data class ItemPerPage(
        @SerializedName("content") val content: Int
)
