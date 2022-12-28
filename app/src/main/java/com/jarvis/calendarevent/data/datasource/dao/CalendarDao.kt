package com.jarvis.calendarevent.data.datasource.dao

import androidx.room.*
import com.jarvis.calendarevent.domain.model.entity.CalendarEntity

@Dao
interface CalendarDao {
    @Insert
    suspend fun insertDataCalendar(calendarEntity: CalendarEntity)
}
