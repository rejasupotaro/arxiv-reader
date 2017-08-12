package rejasupotaro.arxiv.reader.model

import com.google.gson.annotations.SerializedName

data class Paper(
        @SerializedName("id")
        var url: String = "",

        @SerializedName("title")
        var title: String = "",

        @SerializedName("summary")
        var summary: String = "",

        @SerializedName("link")
        var links: List<Link> = listOf(),

        @SerializedName("updated")
        var updated: String = "",

        @SerializedName("published")
        var published: String = ""
) {
    @SerializedName("download_url")
    var downloadUrl: String = ""
        get() = links.filter { it.title == "pdf" }.first().href
}
