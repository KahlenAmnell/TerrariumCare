package com.example.terrariumcare.data.repository

import com.example.terrariumcare.data.local.dao.TerrariumDao
import com.example.terrariumcare.data.local.entities.TerrariumEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TerrariumRepository @Inject constructor(
    private val terrariumDao: TerrariumDao
) {
    val allTerrariums: Flow<List<TerrariumEntity>> = terrariumDao.getAllTerrariums()

    fun observeTerrariumById(id: Long): Flow<TerrariumEntity?> {
        return terrariumDao.observeTerrariumById(id)
    }

    suspend fun getTerrariumById(id: Long): TerrariumEntity? {
        return terrariumDao.getTerrariumById(id)
    }

    suspend fun insertTerrarium(terrarium: TerrariumEntity): Long {
        return terrariumDao.insertTerrarium(terrarium)
    }

    suspend fun updateTerrarium(terrarium: TerrariumEntity) {
        terrariumDao.updateTerrarium(terrarium)
    }

    suspend fun deleteTerrarium(terrarium: TerrariumEntity) {
        terrariumDao.deleteTerrarium(terrarium)
    }

    suspend fun deleteTerrariumById(id: Long) {
        terrariumDao.deleteTerrariumById(id)
    }
}
