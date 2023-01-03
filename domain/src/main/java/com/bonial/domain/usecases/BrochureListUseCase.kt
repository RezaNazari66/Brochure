package com.bonial.domain.usecases

import com.bonial.domain.models.BrochureListResponse
import com.bonial.domain.repositories.BrochuresRepository
import com.bonial.domain.base.usecase.base.AsyncSuspendUseCaseNoParam
import javax.inject.Inject

class BrochureListUseCase  @Inject constructor(private val repository: BrochuresRepository) :
    AsyncSuspendUseCaseNoParam<BrochureListResponse> {

    override suspend fun executeAsync(): BrochureListResponse =
        repository.fetchBrochureList()


}