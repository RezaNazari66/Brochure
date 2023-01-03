package com.bonial.brochures.di

import com.bonial.data.repositories.BrochuresRepositoryImpl
import com.bonial.domain.repositories.BrochuresRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [RepositoryModule.Binder::class])
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class Binder {

        @Binds
        @Singleton
        abstract fun bindBrochuresRepository(brochuresRepositoryImpl: BrochuresRepositoryImpl): BrochuresRepository

    }

}