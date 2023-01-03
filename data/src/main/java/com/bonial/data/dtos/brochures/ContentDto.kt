package com.bonial.data.dtos.brochures


import com.google.gson.*
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Type

data class ContentDto(
    @SerializedName("contentType")
    val contentType: ContentTypeDto?,
    @SerializedName("content")
    val innerContent: InnerContentDto?,
) {

    class ContentDtoDeserializer : JsonDeserializer<ContentDto?> {
        @Throws(JsonParseException::class)

        override fun deserialize(
            json: JsonElement,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): ContentDto {
            val jsonObject = json.asJsonObject
            val contentType = jsonObject.get("contentType").asString

            //todo this strings repeated twice
            val contentDto: ContentDto =
                if (contentType == "brochure" || contentType == "brochurePremium") {
                    Gson().fromJson(json, ContentDto::class.java)
                } else {
                    val contentTypeEnum =
                        Gson().fromJson(jsonObject.get("contentType"), ContentTypeDto::class.java)
                    ContentDto(
                        contentType = contentTypeEnum,
                        innerContent = null,
                    )
                }
            return contentDto
        }
    }

}