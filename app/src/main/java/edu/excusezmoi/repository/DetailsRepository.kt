package edu.excusezmoi.repository

import edu.excusezmoi.persistence.BanEntity
import edu.excusezmoi.persistence.CustomExcuseEntity
import edu.excusezmoi.persistence.ExcuseDao
import edu.excusezmoi.persistence.ModificationEntity
import edu.excusezmoi.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class DetailsRepository constructor(
    private val excuseDao: ExcuseDao,
){
    suspend fun addCustom(category: String, excuse: String): Flow<DataState<Long>> = flow {
        emit(DataState.Loading)
        try {
            val row = excuseDao.insertCustom(CustomExcuseEntity(1, category, excuse))
            emit(DataState.Success(row))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun addBan(id: Int): Flow<DataState<Long>> = flow {
        emit(DataState.Loading)
        try {
            val row = excuseDao.insertBan(BanEntity(id))
            emit(DataState.Success(row))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun addModification(id: Int, category: String, excuse: String): Flow<DataState<Long>> = flow {
        emit(DataState.Loading)
        try {
            val row = excuseDao.insertModification(ModificationEntity(id, category, excuse))
            emit(DataState.Success(row))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}
