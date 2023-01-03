package com.bonial.domain.repositories

import com.bonial.domain.models.BrochureListResponse
import com.bonial.shared.sharedmodel.ResultModel

interface BrochuresRepository {

    suspend fun fetchBrochureList(): ResultModel<BrochureListResponse>

}