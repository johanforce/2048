package com.jarvis.calendarevent.data.repository.impl

import com.haroldadmin.cnradapter.NetworkResponse
import com.jarvis.calendarevent.common.DataUtils
import com.jarvis.calendarevent.common.data.StateData
import com.jarvis.calendarevent.data.remote.ZingApi
import com.jarvis.calendarevent.data.repository.repo.ZingRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ZingRepositoryImpl @Inject constructor(
    private val zingApi: ZingApi
) : ZingRepository {
    override suspend fun fetchDataHome(): StateData<String> {
        val data: StateData<String> = StateData()
        val cTime = System.currentTimeMillis()
        val path = "/api/v2/page/get/home"
        val count = 10
        when (val response = zingApi.fetchDataHome(
            page = 1,
            segmentId = -1,
            count = count,
            ctime = cTime,
            version = ZingApi.version,
            sig = DataUtils.hashParamHome(path, cTime, count, ZingApi.version),
            apiKey = ZingApi.apiKey
        )) {
            is NetworkResponse.Success -> {
                response.body
                    .let {
                        data.data = response.body
                        data.status = StateData.DataStatus.SUCCESS
                    }
            }
            is NetworkResponse.Error -> {
                data.error = response.error
                data.status = StateData.DataStatus.ERROR
            }
            else -> {}
        }
        return data
    }

    override suspend fun fetchDataSongWithId(decodeId: String): StateData<String> {
        val data: StateData<String> = StateData()
        val cTime = System.currentTimeMillis()
        val path = "/api/v2/song/get/streaming"
        when (val response = zingApi.fetchDataSongWithId(
            version = ZingApi.version,
            apiKey = ZingApi.apiKey,
            id = decodeId,
            ctime = cTime,
            sig = DataUtils.hashParam(path, decodeId, cTime)
        )) {
            is NetworkResponse.Success -> {
                response.body.let {
                    data.data = it
                    data.status = StateData.DataStatus.SUCCESS
                }
            }
            is NetworkResponse.Error -> {
                data.error = response.error
                data.status = StateData.DataStatus.ERROR
            }
            else -> {}
        }
        return data
    }
}
