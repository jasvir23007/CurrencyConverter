package com.rockmvvm.rockbasemvvm

import android.app.Activity
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

    override fun applicationInjector(): AndroidInjector<out MyApplication> {
        return DaggerAppInjector.builder().create(this)
    }


    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }


    override fun onCreate() {
        super.onCreate()
    }

}