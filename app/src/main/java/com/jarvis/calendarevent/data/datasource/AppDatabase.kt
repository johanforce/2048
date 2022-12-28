package com.jarvis.calendarevent.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jarvis.calendarevent.domain.model.entity.CalendarEntity
import com.jarvis.calendarevent.data.datasource.dao.CalendarDao

@Database(
    entities = [CalendarEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun calendarDao(): CalendarDao

    companion object {
        const val DATABASE_NAME = "calendar_database"
    }
}
