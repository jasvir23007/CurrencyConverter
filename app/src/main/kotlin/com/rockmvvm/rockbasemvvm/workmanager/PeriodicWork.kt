package com.rockmvvm.rockbasemvvm.workmanager

import android.app.Activity
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.rockmvvm.rockbasemvvm.MyApplication
import com.rockmvvm.rockbasemvvm.data.DataManager
import com.rockmvvm.rockbasemvvm.di.component.DaggerAppInjector
import com.rockmvvm.rockbasemvvm.util.rx.SchedulerProvider
import dagger.android.AndroidInjection
import javax.inject.Inject
import android.app.ActivityManager
import android.content.ComponentName


class PeriodicWork(var ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    @Inject
    lateinit var mDataManager: DataManager

    @Inject
    lateinit var mSchedulerProvider: SchedulerProvider



    fun inject(activity: Activity){
        AndroidInjection.inject(activity)
    }


    override fun doWork(): Result {




        val map = HashMap<String, String>()
        map.put("access_key", "a70973020b8650e5743e66eba2fd807b")
        mDataManager.doApiCurrencyCall(map)
            .subscribeOn(mSchedulerProvider.io())
            .observeOn(mSchedulerProvider.ui())
            .subscribe(
                { result ->
                    mDataManager.saveData(Gson().toJson(result))

                },
                {
                    Result.retry();
                })



        return Result.success()

    }


}