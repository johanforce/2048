package com.jarvis.calendarevent.di

import com.google.gson.Gson
import com.jarvis.calendarevent.data.remote.ApiService
import com.jarvis.calendarevent.data.remote.ZingApi
import com.jarvis.calendarevent.data.remote.ZingServerTauApi
import com.jarvis.calendarevent.presentation.base.BaseSourceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Declare network component
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    @BaseSourceApi("logging")
    fun provideLogging(): Interceptor {
        return ApiService.createLoggingInterceptor()
    }

    @Provides
    @Singleton
    @BaseSourceApi("header")
    fun provideHeader(): Interceptor {
        return ApiService.createHeaderInterceptor()
    }

    @Provides
    @Singleton
    @BaseSourceApi("base_url")
    fun provideSampleOkHttpClient(
        @BaseSourceApi("logging") logging: Interceptor,
        @BaseSourceApi("header") header: Interceptor
    ): OkHttpClient {
        return ApiService.createOkHttpClient(logging, header)
    }

    @Provides
    @Singleton
    @BaseSourceApi("base_url")
    fun provideSampleRetrofit(
        @BaseSourceApi("base_url") okHttpClient: OkHttpClient
    ): Retrofit {
        return ApiService.createRetrofit(okHttpClient)
    }

    @Provides
    @Singleton
    @BaseSourceApi("base_server_tau")
    fun provideServerTauRetrofit(
        @BaseSourceApi("base_url") okHttpClient: OkHttpClient
    ): Retrofit {
        return ApiService.createRetrofitServerTau(okHttpClient)
    }

    @Provides
    @Singleton
    fun provideApiService(@BaseSourceApi("base_url") retrofit: Retrofit): ZingApi {
        return retrofit.create(ZingApi::class.java)
    }

    @Provides
    @Singleton
    fun provideApiServiceTau(@BaseSourceApi("base_server_tau") retrofit: Retrofit): ZingServerTauApi {
        return retrofit.create(ZingServerTauApi::class.java)
    }
}
