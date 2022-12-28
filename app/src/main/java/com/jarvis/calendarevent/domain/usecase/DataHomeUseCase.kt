package com.jarvis.calendarevent.domain.usecase

import com.jarvis.calendarevent.common.data.StateData
import com.jarvis.calendarevent.data.repository.repo.ZingRepository
import javax.inject.Inject

class DataHomeUseCase @Inject constructor() {

    @Inject
    lateinit var zingRepository: ZingRepository

    suspend operator fun invoke(): StateData<String> {
        return zingRepository.fetchDataHome()
    }
}
