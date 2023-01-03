package com.bonial.data.dtos.brochures


import com.google.gson.annotations.SerializedName
import com.bonial.data.base.Dto

data class BrochureListResponseDto(
    @SerializedName("_embedded")
    val embedded: EmbeddedDto?,
): Dto