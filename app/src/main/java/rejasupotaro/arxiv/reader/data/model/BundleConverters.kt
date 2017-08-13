package rejasupotaro.arxiv.reader.data.model

import com.yatatsu.autobundle.AutoBundleConverter
import rejasupotaro.arxiv.reader.data.gson

class PaperConverter : AutoBundleConverter<Paper, String> {

    override fun convert(paper: Paper): String {
        return gson.toJson(paper)
    }

    override fun original(value: String): Paper {
        return gson.fromJson(value, Paper::class.java)
    }
}

