package rejasupotaro.arxiv.reader.model

import com.google.gson.annotations.SerializedName

data class Link(
        @SerializedName("@title") val title: String,
        @SerializedName("@href") val href: String,
        @SerializedName("@rel") val rel: String,
        @SerializedName("@type") val type: String
)

