package com.bonial.data.services

import com.bonial.data.dtos.brochures.BrochureListResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface BrochuresEndPoints {
    @GET("/stories-test/shelf.json")
    suspend fun fetchBrochureList(): Response<BrochureListResponseDto>
}