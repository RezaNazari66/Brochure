package com.bonial.data.dtos.brochures


import com.google.gson.annotations.SerializedName

data class RetailerDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)