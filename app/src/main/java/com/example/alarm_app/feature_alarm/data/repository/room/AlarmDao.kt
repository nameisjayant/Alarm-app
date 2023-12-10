package com.example.alarm_app.feature_alarm.data.repository.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.alarm_app.feature_alarm.domain.model.Alarm
import kotlinx.coroutines.flow.Flow


@Dao
interface AlarmDao {

    @Query("select * from alarm")
    fun getAllAlarms(): Flow<List<Alarm>>

    @Query("select * from alarm where id = :id")
    fun getAlarm(id: Int): Alarm?

    @Query("select * from alarm where timeInMinutes = :timeInMinutes")
    suspend fun getAlarmByPickedTime(timeInMinutes: Int): Alarm?

    @Query("select * from alarm where isEnabled = 1")
    fun getEnabledAlarms(): Flow<List<Alarm>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlarm(alarm: Alarm)

    @Delete
    suspend fun deleteAlarm(alarm: Alarm)

}