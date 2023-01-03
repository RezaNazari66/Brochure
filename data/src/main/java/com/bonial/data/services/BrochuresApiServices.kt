package com.bonial.data.services

import com.bonial.data.datasources.BrochuresRemoteDataSource
import com.bonial.domain.models.BrochureListResponse
import com.bonial.data.base.BaseApiService
import com.bonial.data.dtos.brochures.BrochureListResponseDtoMapper
import javax.inject.Inject

class BrochuresApiServices @Inject constructor(
    private val brochureListResponseDtoMapper: BrochureListResponseDtoMapper,
) : BaseApiService<BrochuresEndPoints>(BrochuresEndPoints::class.java), BrochuresRemoteDataSource {


    override suspend fun fetchBrochureList(): BrochureListResponse =
        brochureListResponseDtoMapper.mapToDataModel(executionWithMessage { apiService.fetchBrochureList() }.data)

}