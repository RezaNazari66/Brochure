package com.bonial.brochures

import timber.log.Timber
import com.bonial.log.Logger
import javax.inject.Inject

class AppLogger @Inject constructor(): Logger {

    override fun error(message: String) {
        Timber.e(message)
    }

    override fun error(throwable: Throwable) {
        Timber.e(throwable)
    }

    override fun warn(message: String) {
        Timber.w(message)
    }

    override fun warn(throwable: Throwable) {
        Timber.w(throwable)
    }

    override fun info(message: String) {
        Timber.i(message)
    }

}