package com.jarvis.calendarevent.domain.usecase

import com.jarvis.calendarevent.common.data.StateData
import com.jarvis.calendarevent.data.repository.repo.ZingRepository
import javax.inject.Inject

class SongStreamUseCase @Inject constructor() {

    @Inject
    lateinit var zingRepository: ZingRepository

    suspend operator fun invoke(decodeId: String): StateData<String> {
        return zingRepository.fetchDataSongWithId(decodeId)
    }
}
