package com.bonial.domain.base.usecase.base

interface SyncUseCase<RQ, RS> {
    fun executeSync(rq: RQ): RS
}