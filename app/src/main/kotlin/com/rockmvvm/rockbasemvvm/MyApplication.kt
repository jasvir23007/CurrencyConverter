package com.rockmvvm.rockbasemvvm

import android.app.Activity
import androidx.work.Configuration
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import com.rockmvvm.rockbasemvvm.di.component.AppInjector
import com.rockmvvm.rockbasemvvm.di.component.DaggerAppInjector
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

/**
 *
 * Created by jasvir on 2019-06-29
 *
 **/

open class MyApplication : DaggerApplication() {

    var mCurrentActivity: Activity? = null
    lateinit var appInjector: AndroidInjector<MyApplication>

    override fun applicationInjector(): AndroidInjector<out MyApplication> {
        return DaggerAppInjector.builder().create(this)
    }

    @Inject
    lateinit var workerFactory: WorkerFactory


    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }




    override fun onCreate() {
        super.onCreate()

        //applicationInjector().inject()
        configureWorkManager()
    }


    fun getCurrentActivity(): Activity? {
        return mCurrentActivity
    }

    fun setCurrentActivity(mCurrentActivity: Activity) {
        this.mCurrentActivity = mCurrentActivity
    }

    private fun configureWorkManager() {
        val config = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

        WorkManager.initialize(this, config)
    }
}