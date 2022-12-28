package com.jarvis.calendarevent.data.repository.repo

import com.jarvis.calendarevent.common.data.StateData

interface ZingRepository {
    suspend fun fetchDataHome(): StateData<String>
    suspend fun fetchDataSongWithId(decodeId: String): StateData<String>
}
