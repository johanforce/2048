package com.jarvis.calendarevent.domain.usecase

import com.jarvis.calendarevent.common.data.StateData
import com.jarvis.calendarevent.data.repository.repo.ZingRepository
import com.jarvis.calendarevent.data.repository.repo.ZingServerTauRepository
import javax.inject.Inject

class GraphHomeUseCase @Inject constructor() {

    @Inject
    lateinit var zingServerTauRepository: ZingServerTauRepository

    suspend operator fun invoke(): StateData<String> {
        return zingServerTauRepository.fetchGraphHome()
    }
}
