package edu.excusezmoi.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.excusezmoi.persistence.ExcuseDao
import edu.excusezmoi.persistence.ExcuseDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Singleton
    @Provides
    fun provideExcuseDb(@ApplicationContext context: Context): ExcuseDatabase {
        return Room.databaseBuilder(
            context,
            ExcuseDatabase::class.java,
            ExcuseDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideExcuseDao(excuseDatabase: ExcuseDatabase): ExcuseDao {
        return excuseDatabase.excuseDao()
    }
}
