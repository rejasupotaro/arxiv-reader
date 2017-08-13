package rejasupotaro.arxiv.reader.data.api

import com.google.gson.annotations.SerializedName

data class LinkEntity(
        @SerializedName("title") val title: String,
        @SerializedName("href") val href: String,
        @SerializedName("rel") val rel: String,
        @SerializedName("type") val type: String
)

