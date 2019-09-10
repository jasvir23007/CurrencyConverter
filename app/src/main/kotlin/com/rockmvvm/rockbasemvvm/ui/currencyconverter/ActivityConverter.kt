package com.rockmvvm.rockbasemvvm.ui.currencyconverter

import android.app.Activity
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.work.*
import com.rockmvvm.rockbasemvvm.BR
import com.rockmvvm.rockbasemvvm.R
import com.rockmvvm.rockbasemvvm.databinding.ActivityHomeBinding
import com.rockmvvm.rockbasemvvm.ui.base.BaseActivity
import com.rockmvvm.rockbasemvvm.workmanager.PeriodicWork
import dagger.android.DispatchingAndroidInjector
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ActivityConverter : BaseActivity<ActivityHomeBinding, CurrencyViewModel>() {
    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun getViewModel(): CurrencyViewModel {
        mViewModel = ViewModelProviders.of(this, factory).get(CurrencyViewModel::class.java)
        return mViewModel as CurrencyViewModel
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        periodicDBUpdate()
    }


    fun periodicDBUpdate() {
        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest =
            PeriodicWorkRequest.Builder(PeriodicWork::class.java, 5, TimeUnit.MINUTES)
                .setConstraints(constraint)
                .build()


        val workManager = WorkManager.getInstance(this)
        workManager.enqueueUniquePeriodicWork(
            "my_unique_worker",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )

    }


}
