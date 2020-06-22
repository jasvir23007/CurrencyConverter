package com.rockmvvm.rockbasemvvm.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.rockmvvm.rockbasemvvm.MyApplication
import com.rockmvvm.rockbasemvvm.constants.Constants.ACCESS_KEY
import com.rockmvvm.rockbasemvvm.constants.Constants.ACCESS_KEY_VALUE
import com.rockmvvm.rockbasemvvm.data.DataManager
import com.rockmvvm.rockbasemvvm.util.rx.SchedulerProvider
import javax.inject.Inject


class PeriodicWork(var ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    @Inject
    lateinit var mDataManager: DataManager

    @Inject
    lateinit var mSchedulerProvider: SchedulerProvider


    override fun doWork(): Result {
        val map = HashMap<String, String>()
        map.put(ACCESS_KEY, ACCESS_KEY_VALUE)
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