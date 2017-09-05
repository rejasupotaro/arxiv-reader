package rejasupotaro.arxiv.reader.data.model

import com.yatatsu.autobundle.AutoBundleConverter
import rejasupotaro.arxiv.reader.data.gson

class PaperConverter : AutoBundleConverter<Paper, String> {
    override fun convert(paper: Paper): String = gson.toJson(paper)
    override fun original(value: String): Paper = gson.fromJson(value, Paper::class.java)
}

