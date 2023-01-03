package com.bonial.data.base

import com.bonial.domain.base.DataModel

interface DtoMapper<in T: Dto, out D: DataModel> {

    fun mapToDataModel(dto: T?): D

}