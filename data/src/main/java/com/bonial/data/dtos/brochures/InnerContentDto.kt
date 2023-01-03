package com.bonial.data.dtos.brochures


import com.google.gson.annotations.SerializedName

data class InnerContentDto(
    @SerializedName("brochureImage")
    val brochureImage: String?,
    @SerializedName("retailer")
    val retailer: RetailerDto?,
)