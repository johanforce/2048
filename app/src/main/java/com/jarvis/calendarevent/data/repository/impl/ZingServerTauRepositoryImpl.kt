package com.jarvis.calendarevent.data.repository.impl

import com.haroldadmin.cnradapter.NetworkResponse
import com.jarvis.calendarevent.common.DataUtils
import com.jarvis.calendarevent.common.data.StateData
import com.jarvis.calendarevent.data.remote.ZingApi
import com.jarvis.calendarevent.data.remote.ZingServerTauApi
import com.jarvis.calendarevent.data.repository.repo.ZingServerTauRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ZingServerTauRepositoryImpl @Inject constructor(
    private val zingServerTauApi: ZingServerTauApi
) : ZingServerTauRepository {

    override suspend fun fetchGraphHome(): StateData<String> {
        val data: StateData<String> = StateData()
        val cTime = System.currentTimeMillis()
        val path = "/api/v2/page/get/chart-home"
        val sig = DataUtils.hashParamNoId(path, cTime, ZingApi.version)
        when (val response = zingServerTauApi.fetchGraphHome()) {
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
