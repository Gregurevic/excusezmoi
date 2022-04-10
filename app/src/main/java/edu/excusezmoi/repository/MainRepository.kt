package edu.excusezmoi.repository

import edu.excusezmoi.model.Excuse
import edu.excusezmoi.network.ExcuseNetworkMapper
import edu.excusezmoi.network.ExcuseService
import edu.excusezmoi.persistence.ExcuseCacheMapper
import edu.excusezmoi.persistence.ExcuseDao
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
    suspend fun getExcuse(): Flow<DataState<List<Excuse>>> = flow {
        emit(DataState.Loading)
        try {
            val networkExcuse = excuseService.getExcuse()
            val excuse = excuseNetworkMapper.entityToModel(networkExcuse)
            excuseDao.insert(excuseCacheMapper.modelToEntity(excuse))
            val cachedExcuses = excuseDao.getAll()
            emit(DataState.Success(excuseCacheMapper.entityListToModelList(cachedExcuses)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    ///...
}