package com.example.terrariumcare.di

import android.content.Context
import com.example.terrariumcare.data.local.AppDatabase
import com.example.terrariumcare.data.local.dao.AnimalDao
import com.example.terrariumcare.data.local.dao.LogEntryDao
import com.example.terrariumcare.data.local.dao.TerrariumDao
import com.example.terrariumcare.data.repository.TerrariumRepository
import com.example.terrariumcare.utils.ImageStorageManager
import com.example.terrariumcare.utils.ImageStorageManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideTerrariumDao(database: AppDatabase): TerrariumDao {
        return database.terrariumDao()
    }

    @Provides
    fun provideAnimalDao(database: AppDatabase): AnimalDao {
        return database.animalDao()
    }

    @Provides
    fun provideLogEntryDao(database: AppDatabase): LogEntryDao {
        return database.logEntryDao()
    }

    @Provides
    @Singleton
    fun provideTerrariumRepository(terrariumDao: TerrariumDao): TerrariumRepository {
        return TerrariumRepository(terrariumDao)
    }

    @Provides
    @Singleton
    fun provideImageStorageManager(
        impl: ImageStorageManagerImpl
    ): ImageStorageManager = impl
}
