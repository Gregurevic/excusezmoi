package edu.excusezmoi.repository

import edu.excusezmoi.model.Excuse
import edu.excusezmoi.network.ExcuseService
import edu.excusezmoi.persistence.*
import edu.excusezmoi.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class DetailsRepository constructor(
    private val excuseDao: ExcuseDao,
    private val excuseCacheMapper: ExcuseCacheMapper,
    private val excuseService: ExcuseService
){
    suspend fun createExcuse(category: String, excuse: String): Flow<DataState<List<Excuse>>> = flow {
        emit(DataState.Loading)
        try {
            excuseService.postExcuse(category, excuse)
            emit(DataState.Success(excuseCacheMapper.entityListToModelList(excuseDao.selectExcuses())))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun updateExcuse(id: Int, category: String, excuse: String): Flow<DataState<List<Excuse>>> = flow {
        emit(DataState.Loading)
        try {
            excuseService.patchExcuse(id, category, excuse)
            emit(DataState.Success(excuseCacheMapper.entityListToModelList(excuseDao.selectExcuses())))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun destroyExcuse(id: Int): Flow<DataState<List<Excuse>>> = flow {
        emit(DataState.Loading)
        try {
            excuseService.destroyExcuse(id)
            emit(DataState.Success(excuseCacheMapper.entityListToModelList(excuseDao.selectExcuses())))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}
