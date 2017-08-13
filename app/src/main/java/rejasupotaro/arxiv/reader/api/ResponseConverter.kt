package rejasupotaro.arxiv.reader.api

import fr.arnaudguyon.xmltojsonlib.XmlToJson
import org.json.JSONObject
import rejasupotaro.arxiv.reader.gson

object ResponseConverter {
    fun xmlToPaper(xml: String): PaperEntity {
        val json = xmlToJson(xml)
        return gson.fromJson(json.getJSONObject("entry").toString(), PaperEntity::class.java)
    }

    fun xmlToApiResponse(xml: String): ApiResponse {
        val json = xmlToJson(xml)
        return gson.fromJson(json.getJSONObject("feed").toString(), ApiResponse::class.java)
    }

    private fun xmlToJson(xml: String): JSONObject {
        return XmlToJson.Builder(xml)
                .forceList("/entry/author")
                .forceList("/entry/category")
                .forceList("/entry/link")
                .forceList("/feed/entry")
                .forceList("/feed/entry/author")
                .forceList("/feed/entry/category")
                .forceList("/feed/entry/link")
                .build().toJson() ?: JSONObject()
    }
}
