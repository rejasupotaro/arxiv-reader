package rejasupotaro.arxiv.reader.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
        @SerializedName("id") val id: String,
        @SerializedName("updated") val updated: String,
        @SerializedName("<opensearch>totalResults") val totalResults: Int,
        @SerializedName("<opensearch>:startIndex") val startIndex: Int,
        @SerializedName("<opensearch>:itemsPerPage") val itemPerPage: Int,
        @SerializedName("entry") val papers: List<Paper>
)

