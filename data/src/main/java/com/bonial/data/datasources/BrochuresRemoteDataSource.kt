package com.bonial.data.datasources

import com.bonial.domain.models.BrochureListResponse

interface BrochuresRemoteDataSource {

    suspend fun fetchBrochureList(): BrochureListResponse

}