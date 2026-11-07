package com.example.terrariumcare.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.terrariumcare.data.local.converters.DateConverters
import com.example.terrariumcare.data.local.converters.EnumConverters
import com.example.terrariumcare.data.local.dao.AnimalDao
import com.example.terrariumcare.data.local.dao.LogEntryDao
import com.example.terrariumcare.data.local.dao.TerrariumDao
import com.example.terrariumcare.data.local.entities.AnimalEntity
import com.example.terrariumcare.data.local.entities.LogEntryEntity
import com.example.terrariumcare.data.local.entities.TerrariumEntity

@Database(
    entities = [TerrariumEntity::class, AnimalEntity::class, LogEntryEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverters::class, EnumConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun terrariumDao(): TerrariumDao
    abstract fun animalDao(): AnimalDao
    abstract fun logEntryDao(): LogEntryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "terrarium_care_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
