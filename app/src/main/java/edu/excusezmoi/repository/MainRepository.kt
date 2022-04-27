package edu.excusezmoi.repository

import edu.excusezmoi.model.Excuse
import edu.excusezmoi.network.ExcuseNetworkMapper
import edu.excusezmoi.network.ExcuseService
import edu.excusezmoi.persistence.*
import edu.excusezmoi.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MainRepository constructor(
    private val excuseDao: ExcuseDao,
    private val excuseService: ExcuseService,
    private val excuseCacheMapper: ExcuseCacheMapper,
    private val excuseNetworkMapper: ExcuseNetworkMapper
) {

    suspend fun getCurrentExcuses(): Flow<DataState<List<Excuse>>> = flow {
        emit(DataState.Loading)
        try {
            val excuses: List<Excuse> = excuseCacheMapper.entityListToModelList(excuseDao.selectExcuses())
            if (excuses.isNotEmpty()) emit(DataState.Success(excuses))
            else {
                getNewExcuses()
                emit(DataState.Success(excuseCacheMapper.entityListToModelList(excuseDao.selectExcuses())))
            }
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun getNewExcuses(): Flow<DataState<List<Excuse>>> = flow {
        emit(DataState.Loading)
        try {
            val newExcuses = excuseNetworkMapper.entityListToModelList(excuseService.getExcuses())
            excuseDao.nukeExcuses()
            excuseDao.insertExcuses(excuseCacheMapper.modelListToEntityList(newExcuses))
            emit(DataState.Success(excuseCacheMapper.entityListToModelList(excuseDao.selectExcuses())))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun getNewExcusesByCategory(category: String): Flow<DataState<List<Excuse>>> = flow {
        emit(DataState.Loading)
        try {
            val newExcuses = excuseNetworkMapper.entityListToModelList(excuseService.getExcuseByCategory(category))
            excuseDao.nukeExcuses()
            excuseDao.insertExcuses(excuseCacheMapper.modelListToEntityList(newExcuses))
            emit(DataState.Success(excuseCacheMapper.entityListToModelList(excuseDao.selectExcuses())))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}
