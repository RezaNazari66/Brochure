package com.bonial.domain.base.usecase.base

interface AsyncUseCase<RQ, RS> {
    fun executeAsync(rq: RQ): RS
}