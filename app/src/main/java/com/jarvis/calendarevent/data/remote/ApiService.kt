package com.jarvis.calendarevent.data.remote

import android.content.Context
import com.google.gson.GsonBuilder
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.jarvis.calendarevent.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Depend on each API, it will be handled particularly
 */
object ApiService {

    private const val TIMEOUT = 10L

    /**
     * Custom [okhttp3.Interceptor] for special header make safe request
     */
    fun createHeaderInterceptor(): Interceptor =
        Interceptor { chain ->
            val request = chain.request()
            val newUrl = request.url.newBuilder()
                .build()
            val newRequest = request.newBuilder()
                .url(newUrl)
//            .header("Content-Type", "application/json")
                .method(request.method, request.body)
                .build()
            chain.proceed(newRequest)
        }

    /**
     * logs request and response information
     * */
    fun createLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    /**
     * Config cache size
     * */
    fun createOkHttpCache(context: Context): Cache =
        Cache(context.cacheDir, (10 * 1024 * 1024).toLong())

    /**
     * Build [okhttp3.OkHttpClient] with custom config
     * */
    fun createOkHttpClient(
        logging: Interceptor,
        header: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .cookieJar(UvCookieJar())
//                .cache(cache)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(header)
            .addInterceptor(logging)
            .build()

    private class UvCookieJar : CookieJar {

        private val cookies = mutableListOf<Cookie>()

        override fun saveFromResponse(url: HttpUrl, cookieList: List<Cookie>) {
            cookies.clear()
            cookies.addAll(cookieList)
        }

        override fun loadForRequest(url: HttpUrl): List<Cookie> =
            cookies
    }

    /**
     * Build retrofit
     * */

    private val gson = GsonBuilder().create()

    fun createRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()

    fun createRetrofitServerTau(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_SERVER_TAU)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()
}
