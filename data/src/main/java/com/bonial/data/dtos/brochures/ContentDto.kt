package com.bonial.data.dtos.brochures


import com.bonial.domain.models.ContentTypeEnum
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

            val contentDto: ContentDto =
                if (contentType == ContentTypeEnum.BROCHURE.key || contentType == ContentTypeEnum.BROCHURE_PREMIUM.key) {
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