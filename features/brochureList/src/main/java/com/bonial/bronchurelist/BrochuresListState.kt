package com.bonial.bronchurelist

import com.bonial.domain.models.Brochure

sealed class BrochuresListState {
    abstract val data: List<Brochure>
}

data class DefaultState(
    override val data: List<Brochure>
) : BrochuresListState()

data class LoadingState(
    override val data: List<Brochure> = emptyList()
) : BrochuresListState()

data class ErrorState(
    val errorMessage: String,
    override val data: List<Brochure> = emptyList()
) : BrochuresListState()