package com.bonial.data.adapter

interface CallAdapter<R> {
    suspend fun execute(): R
}