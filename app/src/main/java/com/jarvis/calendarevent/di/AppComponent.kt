package com.jarvis.calendarevent.di

import android.app.Application
import androidx.room.Room
import com.jarvis.calendarevent.data.datasource.AppDatabase
import com.jarvis.calendarevent.data.repository.repo.ZingRepository
import com.jarvis.calendarevent.data.repository.impl.ZingRepositoryImpl
import com.jarvis.calendarevent.data.repository.impl.ZingServerTauRepositoryImpl
import com.jarvis.calendarevent.data.repository.repo.ZingServerTauRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppComponent {
    @Provides
    @Singleton
    fun provideDatabase(application: Application) = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        AppDatabase.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideZingRepository(weatherRepositoryImpl: ZingRepositoryImpl): ZingRepository {
        return weatherRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideZingServerTauRepository(zingServerTauRepositoryImpl: ZingServerTauRepositoryImpl): ZingServerTauRepository {
        return zingServerTauRepositoryImpl
    }
}
