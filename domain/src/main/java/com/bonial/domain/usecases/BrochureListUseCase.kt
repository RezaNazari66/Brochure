package com.bonial.domain.usecases

import com.bonial.domain.base.usecase.SupplierUseCase
import com.bonial.domain.base.usecase.UseCaseResult
import com.bonial.domain.models.BrochureListResponse
import com.bonial.domain.repositories.BrochuresRepository
import javax.inject.Inject

class BrochureListUseCase  @Inject constructor(private val repository: BrochuresRepository) :
    SupplierUseCase<BrochureListResponse>() {

    override suspend fun task(): UseCaseResult<BrochureListResponse> {
        return UseCaseResult(repository.fetchBrochureList())
    }
}