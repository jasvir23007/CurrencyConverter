package com.rockmvvm.rockbasemvvm.ui.currencyconverter

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.work.*
import com.rockmvvm.rockbasemvvm.R

import com.rockmvvm.rockbasemvvm.BR
import com.rockmvvm.rockbasemvvm.constants.Constants.REFRESH_RATE
import com.rockmvvm.rockbasemvvm.constants.Constants.UNIQUE_WORKER
import com.rockmvvm.rockbasemvvm.databinding.ActivityHomeBinding
import com.rockmvvm.rockbasemvvm.ui.base.BaseActivity
import com.rockmvvm.rockbasemvvm.workmanager.PeriodicWork
import kotlinx.android.synthetic.main.activity_home.*
import java.util.concurrent.TimeUnit


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
        mViewDataBinding.setLifecycleOwner(this)
        periodicDBUpdate()
        mViewModel?.liveDataCurrency?.observe(this, Observer {
            setSpinner(it)
        })

    }


    fun periodicDBUpdate() {
        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest =
            PeriodicWorkRequest.Builder(PeriodicWork::class.java, REFRESH_RATE, TimeUnit.MINUTES)
                .setConstraints(constraint)
                .build()


        val workManager = WorkManager.getInstance(this)
        workManager.enqueueUniquePeriodicWork(
            UNIQUE_WORKER,
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )

        WorkManager.getInstance(this)
            .getWorkInfoByIdLiveData(workRequest.id)
            .observe(this, Observer { workInfo ->
                if ((workInfo != null) &&
                    (workInfo.state == WorkInfo.State.ENQUEUED)) {
                    getViewModel().onRetrievePostListSuccess()

                }
            })
    }



    private fun setSpinner(list:ArrayList<String>){
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            list
        )
        spChooseCurrency.setAdapter(adapter)

        spChooseCurrency?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mViewModel?.currencyAdapter?.updateCurrency(position)
            }

        }



    }



}
