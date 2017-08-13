package rejasupotaro.arxiv.reader.api

import com.google.gson.annotations.SerializedName

data class PaperEntity(
        @SerializedName("id")
        var url: String,

        @SerializedName("title")
        var title: String,

        @SerializedName("summary")
        var summary: String,

        @SerializedName("author")
        var authors: List<AuthorEntity>,

        @SerializedName("category")
        var categories: List<CategoryEntity>,

        @SerializedName("link")
        var links: List<LinkEntity>,

        @SerializedName("updated")
        var updated: String,

        @SerializedName("published")
        var published: String
)

data class AuthorEntity(
        @SerializedName("name")
        var name: String
)

data class CategoryEntity(
        @SerializedName("term")
        var term: String
)

