package com.example.terrariumcare.data.local.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.terrariumcare.data.local.AppDatabase
import com.example.terrariumcare.data.local.entities.LogEntryEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LogEntryDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: LogEntryDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.logEntryDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertAndGetLogEntry() = runTest {
        val log = LogEntryEntity(
            parentId = 1L,
            parentType = "ANIMAL",
            eventType = "FEEDING",
            date = "2024-03-01",
            time = "12:00"
        )
        dao.insertLogEntry(log)

        val logs = dao.getLogsForAnimal(1L).first()
        assertEquals(1, logs.size)
        assertEquals("FEEDING", logs[0].eventType)
    }
}
