package com.rockmvvm.rockbasemvvm.di.component

import com.rockmvvm.rockbasemvvm.MyApplication
import com.rockmvvm.rockbasemvvm.di.builder.ActivityBuilder
import com.rockmvvm.rockbasemvvm.di.module.AppModule
import com.rockmvvm.rockbasemvvm.di.module.NetworkModule
import com.rockmvvm.rockbasemvvm.workmanager.DaggerWorkerFactory
import com.rockmvvm.rockbasemvvm.workmanager.PeriodicWork
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 *
 * Created by jasvir on 2019-05-05
 *
 **/
@Singleton
@Component(modules = [AndroidInjectionModule::class, NetworkModule::class, AppModule::class, ActivityBuilder::class])
interface AppInjector : AndroidInjector<MyApplication> {


    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyApplication>()




}