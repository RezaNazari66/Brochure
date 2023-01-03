package com.bonial.domain.models

import com.bonial.domain.base.DataModel

data class BrochureListResponse(
    val brochureList : List<Brochure>?
):DataModel
