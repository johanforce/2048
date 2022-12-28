package com.jarvis.calendarevent.data.repository.repo

import com.jarvis.calendarevent.common.data.StateData

interface ZingServerTauRepository {
    suspend fun fetchGraphHome(): StateData<String>
}
