package com.bonial.shared.sharedmodel

sealed class ResultModel<out V> {
    data class Success<V>(val value: V) : ResultModel<V>()
    data class Error(val error: RestErrorResponse) : ResultModel<Nothing>()
}


data class RestErrorResponse(val status: Int, val message: String)