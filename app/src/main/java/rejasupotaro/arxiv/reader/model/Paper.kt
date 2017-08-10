package rejasupotaro.arxiv.reader.model

import com.google.gson.annotations.SerializedName

data class Paper(
        @SerializedName("id") val id: String,
        @SerializedName("updated") val updated: String,
        @SerializedName("published") val published: String,
        @SerializedName("title") val title: String,
        @SerializedName("summary") val summary: String,
        @SerializedName("link") val links: List<Link>
)

data class Link(
        @SerializedName("@title") val title: String,
        @SerializedName("@href") val href: String,
        @SerializedName("@rel") val rel: String
)
