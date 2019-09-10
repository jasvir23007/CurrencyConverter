package com.rockmvvm.rockbasemvvm.di.module

import androidx.room.Room
import android.content.Context
import com.rockmvvm.rockbasemvvm.MyApplication
import com.rockmvvm.rockbasemvvm.data.DataManager
import com.rockmvvm.rockbasemvvm.data.DataManagerImpl
import com.rockmvvm.rockbasemvvm.data.local.db.AppDatabase
import com.rockmvvm.rockbasemvvm.data.local.db.AppDbHelper
import com.rockmvvm.rockbasemvvm.data.local.db.DbHelper
import com.rockmvvm.rockbasemvvm.data.remote.ApiHelper
import com.rockmvvm.rockbasemvvm.data.remote.AppApiHelper
import com.rockmvvm.rockbasemvvm.di.DatabaseInfo
import com.rockmvvm.rockbasemvvm.util.DATABASE_NAME
import com.rockmvvm.rockbasemvvm.util.rx.SchedulerProvider
import com.rockmvvm.rockbasemvvm.util.rx.SchedulerProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *
 * Created by jasvir on 2019-06-29
 *
 **/
@Module
object AppModule {

    @Provides
    @Singleton
    @JvmStatic
    internal fun provideApiHelper(apiHelper: AppApiHelper): ApiHelper = apiHelper

    @Provides
    @Singleton
    @JvmStatic
    internal fun provideDataManager(dataManagerImpl: DataManagerImpl): DataManager = dataManagerImpl


    @Provides
    @Singleton
    @JvmStatic
    internal fun provideContext(application: MyApplication): Context = application


    @Provides
    @Singleton
    @JvmStatic
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProviderImpl()


    @Provides
    @Singleton
    @JvmStatic
    internal fun providesDbHelper(dbHelper: AppDbHelper): DbHelper = dbHelper


    @Provides
    @Singleton
    @JvmStatic
    @DatabaseInfo
    internal fun provideDatabaseName(): String = DATABASE_NAME


    @Provides
    @Singleton
    @JvmStatic
    internal fun providesDatabase(@DatabaseInfo dbName: String, context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, dbName)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()


}