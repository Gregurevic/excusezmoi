package edu.excusezmoi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.excusezmoi.network.ExcuseNetworkMapper
import edu.excusezmoi.network.ExcuseService
import edu.excusezmoi.persistence.ExcuseCacheMapper
import edu.excusezmoi.persistence.ExcuseDao
import edu.excusezmoi.repository.DetailsRepository
import edu.excusezmoi.repository.MainRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        excuseDao: ExcuseDao,
        excuseService: ExcuseService,
        excuseCacheMapper: ExcuseCacheMapper,
        excuseNetworkMapper: ExcuseNetworkMapper
    ): MainRepository {
        return MainRepository(excuseDao, excuseService, excuseCacheMapper, excuseNetworkMapper)
    }

    @Singleton
    @Provides
    fun provideDetailsRepository(
        ///...
    ): DetailsRepository {
        return DetailsRepository()
    }
}