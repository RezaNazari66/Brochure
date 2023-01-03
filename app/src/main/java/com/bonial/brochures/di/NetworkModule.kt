package com.bonial.brochures.di

import com.bonial.data.dtos.brochures.ContentDto
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://test-mobile-configuration-files.s3.eu-central-1.amazonaws.com/"
    private const val READ_TIMEOUT: Long = 30
    private const val CONNECTION_TIMEOUT: Long = 15
    private const val WRITE_TIMEOUT: Long = 15

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .registerTypeAdapter(
                            ContentDto::class.java,
                            ContentDto.ContentDtoDeserializer()
                        )
                        .setLenient()
                        .create()
                )
            )
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor(loggingInterceptor)
        okHttpBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        okHttpBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        okHttpBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        return okHttpBuilder.build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }


}