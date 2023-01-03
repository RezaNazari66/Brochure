package com.bonial.data.dtos.brochures

import com.google.gson.annotations.SerializedName

enum class ContentTypeDto {
    @SerializedName("brochure")
    BROCHURE,
    @SerializedName("brochurePremium")
    BROCHURE_PREMIUM
}