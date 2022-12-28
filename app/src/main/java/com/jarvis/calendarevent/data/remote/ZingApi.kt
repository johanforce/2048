package com.jarvis.calendarevent.data.remote

import com.haroldadmin.cnradapter.NetworkResponse
import com.jarvis.calendarevent.common.data.ErrorResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ZingApi {
    companion object {
        val zingMp3Url = "https://zingmp3.vn"
        val secretKey = "2aa2d1c561e809b267f3638c4a307aab"
        val apiKey = "88265e23d4284f25963e6eedac8fbfa3"
        val version = "1.6.34";
    }

    @GET("/api/v2/song/get/streaming")
    suspend fun fetchDataSongWithId(
        @Query("version") version: String? = null,
        @Query("apiKey") apiKey: String? = null,
        @Query("id") id: String? = null,
        @Query("ctime") ctime: Long? = null,
        @Query("sig") sig: String? = null,
    ): NetworkResponse<String, ErrorResponse>

    @GET("/api/v2/page/get/home")
    suspend fun fetchDataHome(
        @Query("page") page: Int? = 1,
        @Query("segmentId") segmentId: Int? = -1,
        @Query("count") count: Int? = 10,
        @Query("ctime") ctime: Long? = null,
        @Query("version") version: String? = null,
        @Query("sig") sig: String? = null,
        @Query("apiKey") apiKey: String? = "88265e23d4284f25963e6eedac8fbfa3"
    ): NetworkResponse<String, String>
}
