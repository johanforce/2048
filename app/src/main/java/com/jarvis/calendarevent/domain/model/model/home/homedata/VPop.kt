package com.jarvis.calendarevent.domain.model.model.home.homedata

data class VPop(
    val alias: String,
    val allowAudioAds: Boolean,
    val artists: List<Artist>,
    val artistsNames: String,
    val duration: Int,
    val encodeId: String,
    val genreIds: List<String>,
    val hasLyric: Boolean,
    val indicators: List<Any>,
    val isIndie: Boolean,
    val isOffical: Boolean,
    val isPrivate: Boolean,
    val isWorldWide: Boolean,
    val link: String,
    val mvlink: String,
    val preRelease: Boolean,
    val releaseDate: Int,
    val streamingStatus: Int,
    val thumbnail: String,
    val thumbnailM: String,
    val title: String,
    val username: String,
    val zingChoice: Boolean
)