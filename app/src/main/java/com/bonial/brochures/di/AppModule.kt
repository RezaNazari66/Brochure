package com.bonial.brochures.di

import com.bonial.brochures.AppLogger
import com.bonial.log.Logger
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindLogger(appLogger: AppLogger): Logger

}