package com.jarvis.calendarevent.domain.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calender")
data class CalendarEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,
    @ColumnInfo(name = "countryCode")
    var countryCode: String? = null,
    @ColumnInfo(name = "counties")
    var counties: String? = null,
    @ColumnInfo(name = "date")
    var date: String? = null,
    @ColumnInfo(name = "fixed")
    var fixed: Boolean? = null,
    @ColumnInfo(name = "global")
    var global: Boolean? = null,
    @ColumnInfo(name = "launchYear")
    var launchYear: Int? = null,
    @ColumnInfo(name = "localName")
    var localName: String? = null,
    @ColumnInfo(name = "name")
    var name: String? = null,
    @ColumnInfo(name = "types")
    var types: String? = null
)