package com.bonial.bronchures.di

import com.bonial.data.datasources.BrochuresRemoteDataSource
import com.bonial.data.services.BrochuresApiServices
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [DataSourceModule.Binder::class])
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class Binder {

        @Binds
        @Singleton
        abstract fun bind(brochuresApiServices: BrochuresApiServices): BrochuresRemoteDataSource

    }

}