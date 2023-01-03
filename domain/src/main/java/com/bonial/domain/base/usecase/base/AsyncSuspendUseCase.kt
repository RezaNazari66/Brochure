package com.bonial.domain.base.usecase.base

interface AsyncSuspendUseCase<RQ, RS> {
   suspend fun executeAsync(rq: RQ): RS
}

interface AsyncSuspendUseCaseNoParam<RS> {
   suspend fun executeAsync(): RS
}