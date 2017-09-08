// https://github.com/tensorflow/tensorflow/blob/master/tensorflow/contrib/android/java/org/tensorflow/contrib/android/TensorFlowInferenceInterface.java
package rejasupotaro.arxiv.reader.ml

import android.content.res.AssetManager
import org.tensorflow.Graph
import org.tensorflow.Session
import java.io.InputStream

class TensorflowRunner(
        private val assetManager: AssetManager,
        private val modelName: String
) {
    fun run() {
        val graph: Graph = Graph()
        val sess = Session(graph)
        val runner = sess.runner()

        assetManager.open(modelName).use { inputStream ->
            loadGraph(inputStream, graph)
        }
    }

    fun loadGraph(inputStream: InputStream, graph: Graph) {
        val graphDef = inputStream.readBytes()
        graph.importGraphDef(graphDef)
    }
}

