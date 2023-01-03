package com.bonial.data.adapter

import com.bonial.data.base.ResponseWrapper
import retrofit2.Response

class SingleCallAdapter<T>(
    private val apiCall: suspend () -> Response<T>
) : CallAdapter<ResponseWrapper<T>> {
    override suspend fun execute(): ResponseWrapper<T> {
        return try {
            val execute = apiCall.invoke()
            if (execute.isSuccessful) {
                val response = execute.body()
                ResponseWrapper.Success(response)
            } else {
                throw Exception()
            }

        } catch (exception: Exception) {
            ResponseWrapper.Error(exception)
        }
    }
}