package com.junemon.compose_stable.di

import android.content.Context
import androidx.room.Room
import com.junemon.compose_stable.cache.StopwatchDao
import com.junemon.compose_stable.cache.StopwatchDatabase
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
    fun provideDatabase(@ApplicationContext context: Context): StopwatchDatabase =
        Room.databaseBuilder(context, StopwatchDatabase::class.java, "stopwatch.db")
            .fallbackToDestructiveMigration().build()


    @Provides
    fun provideStopwatchDao(db: StopwatchDatabase): StopwatchDao {
        return db.stopwatchDao()
    }


}