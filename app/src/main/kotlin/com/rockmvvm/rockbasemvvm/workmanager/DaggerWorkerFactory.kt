package com.rockmvvm.rockbasemvvm.workmanager

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.rockmvvm.rockbasemvvm.data.DataManager
import com.rockmvvm.rockbasemvvm.util.rx.SchedulerProvider

class DaggerWorkerFactory(private val mDataManager: DataManager,
                          private val mSchedulerProvider: SchedulerProvider
):  WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {

        val workerKlass = Class.forName(workerClassName).asSubclass(Worker::class.java)
        val constructor = workerKlass.getDeclaredConstructor(Context::class.java, WorkerParameters::class.java)
        val instance = constructor.newInstance(appContext, workerParameters)

        when (instance) {

            is PeriodicWork -> {
                instance.mDataManager = mDataManager
                instance.mSchedulerProvider = mSchedulerProvider

            }
            // optionally, handle other workers
        }

        return instance
    }



}