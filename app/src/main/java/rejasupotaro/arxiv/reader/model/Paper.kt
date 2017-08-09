package rejasupotaro.arxiv.reader.model

data class Paper(
        val id: String,
        val updated: String,
        val published: String,
        val title: String,
        val summary: String,
        val downloadLink: String)
