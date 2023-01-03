package com.bonial.domain.repositories

import com.bonial.domain.models.BrochureListResponse

interface BrochuresRepository {

    suspend fun fetchBrochureList(): BrochureListResponse

}