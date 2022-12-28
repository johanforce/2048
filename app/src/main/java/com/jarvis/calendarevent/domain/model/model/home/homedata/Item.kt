package com.jarvis.calendarevent.domain.model.model.home.homedata

data class Item(
    val adId: String,
    val itemType: String,
    val items: List<ItemX>,
    val link: String,
    val options: Options,
    val pageType: String,
    val sectionId: String,
    val sectionType: String,
    val title: String,
    val viewType: String
)