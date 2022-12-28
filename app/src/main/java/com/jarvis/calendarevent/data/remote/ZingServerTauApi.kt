package com.jarvis.calendarevent.data.remote

import com.haroldadmin.cnradapter.NetworkResponse
import com.jarvis.calendarevent.common.data.ErrorResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ZingServerTauApi {
    @GET("/api/chart-home")
    suspend fun fetchGraphHome(): NetworkResponse<String, String>
}
