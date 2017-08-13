package rejasupotaro.arxiv.reader.data.file

import android.content.Context
import rejasupotaro.arxiv.reader.data.model.Paper
import java.io.File

object FileManager {

    fun paperToFile(context: Context, paper: Paper): File {
        return File(papersDir(context), paper.title)
    }

    private fun papersDir(context: Context): File {
        val dir = File(context.filesDir, "papers")
        if (!dir.exists()) {
            dir.mkdir()
        }
        return dir
    }
}

