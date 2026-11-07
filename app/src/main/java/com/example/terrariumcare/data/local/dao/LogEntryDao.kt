package com.example.terrariumcare.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.terrariumcare.data.local.entities.LogEntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LogEntryDao {

    @Query("SELECT * FROM log_entries WHERE parentId = :parentId AND parentType = :parentType ORDER BY date DESC, time DESC, id DESC")
    fun getLogsForParent(parentId: Long, parentType: String): Flow<List<LogEntryEntity>>

    @Query("SELECT * FROM log_entries WHERE parentId = :animalId AND parentType = 'ANIMAL' ORDER BY date DESC, time DESC, id DESC")
    fun getLogsForAnimal(animalId: Long): Flow<List<LogEntryEntity>>

    @Query("SELECT * FROM log_entries WHERE parentId = :parentId AND parentType = :parentType AND eventType = :eventType ORDER BY date DESC, time DESC")
    fun getLogsByEventType(parentId: Long, parentType: String, eventType: String): Flow<List<LogEntryEntity>>

    @Query("SELECT * FROM log_entries WHERE id = :logId")
    suspend fun getLogById(logId: Long): LogEntryEntity?

    @Query("SELECT * FROM log_entries WHERE parentId = :parentId AND parentType = :parentType AND (measuredTemperature IS NOT NULL OR measuredHumidity IS NOT NULL) ORDER BY date DESC, time DESC LIMIT 1")
    suspend fun getLatestMeasurement(parentId: Long, parentType: String): LogEntryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLogEntry(logEntry: LogEntryEntity): Long

    @Update
    suspend fun updateLogEntry(logEntry: LogEntryEntity)

    @Delete
    suspend fun deleteLogEntry(logEntry: LogEntryEntity)

    @Query("DELETE FROM log_entries WHERE id = :logId")
    suspend fun deleteLogById(logId: Long)
}