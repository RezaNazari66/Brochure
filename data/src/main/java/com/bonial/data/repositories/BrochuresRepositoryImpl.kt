package com.bonial.data.repositories

import com.bonial.data.datasources.BrochuresRemoteDataSource
import com.bonial.domain.models.BrochureListResponse
import com.bonial.domain.repositories.BrochuresRepository
import javax.inject.Inject

class BrochuresRepositoryImpl @Inject constructor(
    private val brochuresRemoteDataSource: BrochuresRemoteDataSource
) : BrochuresRepository {

    override suspend fun fetchBrochureList(): BrochureListResponse {
        return brochuresRemoteDataSource.fetchBrochureList()
    }

}