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
    private val excuseNetworkMapper: ExcuseNetworkMapper,
    private val customExcuseMapper: CustomExcuseMapper,
    private val modificationMapper: ModificationMapper
) {
    companion object {
        val NUMBER_OF_EXCUSES: Int = 8
        val NUMBER_OF_CUSTOMS: Int = 1
    }

    suspend fun getExcuses(): Flow<DataState<List<Excuse>>> = flow {
        emit(DataState.Loading)
        try {
            val cachedExcuses = excuseDao.getAllCached()
            if (cachedExcuses.isEmpty()) {
                getNewExcuses()
                emit(DataState.Success(excuseCacheMapper.entityListToModelList(excuseDao.getAllCached())))
            } else {
                emit(DataState.Success(excuseCacheMapper.entityListToModelList(cachedExcuses)))
            }
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun getNewExcuses(): Flow<DataState<List<Excuse>>> = flow {
        emit(DataState.Loading)
        try {
            val customIds = excuseDao.getCustomIds()
            val excuses: MutableList<Excuse> = (if (customIds.size >= NUMBER_OF_CUSTOMS) {
                val customExcuse = excuseDao.getCustom(customIds.random())
                val networkExcuses = excuseService.getExcuses(NUMBER_OF_EXCUSES - NUMBER_OF_CUSTOMS)
                excuseNetworkMapper.entityListToModelList(networkExcuses) +
                        customExcuseMapper.entityToModel(customExcuse)
            } else {
                val networkExcuses = excuseService.getExcuses(NUMBER_OF_EXCUSES)
                excuseNetworkMapper.entityListToModelList(networkExcuses)
            }).toMutableList()
            excuseDao.deleteAllCached()
            for (i in excuses.indices) {
                while(excuseDao.isBanned(excuses[i].id) || excuses.any{ it.id == excuses[i].id }) {
                    excuses[i] = excuseNetworkMapper.entityToModel(excuseService.getExcuse())
                }
                val modified = excuseDao.getModified(excuses[i].id)
                if (modified != null) modificationMapper.entityToModel(modified)
                    .also { excuses[i] = it }
                excuseDao.insertCacheEntity(excuseCacheMapper.modelToEntity(excuses[i]))
            }
            val cachedExcuses = excuseDao.getAllCached()
            emit(DataState.Success(excuseCacheMapper.entityListToModelList(cachedExcuses)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun getNewExcusesByCategory(category: String): Flow<DataState<List<Excuse>>> = flow {
        emit(DataState.Loading)
        try {
            val customIdsByCategory = excuseDao.getCustomIdsByCategory(category)
            val excuses: MutableList<Excuse> = (if (customIdsByCategory.size >= NUMBER_OF_CUSTOMS) {
                val customExcuse = excuseDao.getCustom(customIdsByCategory.random())
                val networkExcuses = excuseService.getExcusesByCategory(category, NUMBER_OF_EXCUSES - NUMBER_OF_CUSTOMS)
                excuseNetworkMapper.entityListToModelList(networkExcuses) +
                        customExcuseMapper.entityToModel(customExcuse)
            } else {
                val networkExcuses = excuseService.getExcusesByCategory(category, NUMBER_OF_EXCUSES)
                excuseNetworkMapper.entityListToModelList(networkExcuses)
            }).toMutableList()
            excuseDao.deleteAllCached()
            for (i in excuses.indices) {
                while(excuseDao.isBanned(excuses[i].id) || excuses.any{ it.id == excuses[i].id }) {
                    excuses[i] = excuseNetworkMapper.entityToModel(excuseService.getExcuseByCategory(category))
                }
                val modified = excuseDao.getModified(excuses[i].id)
                if (modified != null) modificationMapper.entityToModel(modified)
                    .also { excuses[i] = it }
                excuseDao.insertCacheEntity(excuseCacheMapper.modelToEntity(excuses[i]))
            }
            val cachedExcuses = excuseDao.getAllCached()
            emit(DataState.Success(excuseCacheMapper.entityListToModelList(cachedExcuses)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}
