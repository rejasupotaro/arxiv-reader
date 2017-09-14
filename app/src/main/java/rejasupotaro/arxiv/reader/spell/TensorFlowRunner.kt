package rejasupotaro.arxiv.reader.spell

import android.content.res.AssetManager
import org.tensorflow.contrib.android.TensorFlowInferenceInterface

class TensorFlowRunner(assets: AssetManager) {
    private val inferenceInterface = TensorFlowInferenceInterface(assets, "spell_corrector.pb")
    private val inputName = "lstm_1_input"
    private val outputName = "output_0"
    private val numClasses = CharacterTable.NUM_CLASSES

    val inputOperation
        get() = inferenceInterface.graphOperation(inputName)

    val outputOperation
        get() = inferenceInterface.graphOperation(outputName)

    fun run(input: FloatArray): FloatArray {
        val outputNames = arrayOf(outputName)
        val output = FloatArray(1 * CharacterTable.MAX_LEN * numClasses)
        inferenceInterface.feed(inputName, input, 1, CharacterTable.MAX_LEN.toLong(), numClasses.toLong())
        inferenceInterface.run(outputNames)
        inferenceInterface.fetch(outputName, output)
        return output
    }
}

