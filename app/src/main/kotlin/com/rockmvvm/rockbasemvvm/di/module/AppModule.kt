package com.rockmvvm.rockbasemvvm.di.module

import android.content.Context
import com.rockmvvm.rockbasemvvm.MyApplication
import com.rockmvvm.rockbasemvvm.data.DataManager
import com.rockmvvm.rockbasemvvm.data.DataManagerImpl
import com.rockmvvm.rockbasemvvm.data.remote.ApiHelper
import com.rockmvvm.rockbasemvvm.data.remote.AppApiHelper
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

}