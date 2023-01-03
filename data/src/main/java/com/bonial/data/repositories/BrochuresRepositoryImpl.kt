package com.bonial.data.repositories

import com.bonial.data.datasources.BrochuresRemoteDataSource
import com.bonial.domain.models.BrochureListResponse
import com.bonial.domain.repositories.BrochuresRepository
import com.bonial.shared.sharedmodel.RestErrorResponse
import com.bonial.shared.sharedmodel.ResultModel
import javax.inject.Inject

class BrochuresRepositoryImpl @Inject constructor(
    private val brochuresRemoteDataSource: BrochuresRemoteDataSource
) : BrochuresRepository {

    override suspend fun fetchBrochureList(): ResultModel<BrochureListResponse> {
        return try {
            ResultModel.Success(brochuresRemoteDataSource.fetchBrochureList())
        } catch (e: Exception) {
            //todo handle errors
            ResultModel.Error(RestErrorResponse(0, e.message.orEmpty()))
        }
    }

}