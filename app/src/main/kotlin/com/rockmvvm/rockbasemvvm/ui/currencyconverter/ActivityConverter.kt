package com.rockmvvm.rockbasemvvm.ui.currencyconverter

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.rockmvvm.rockbasemvvm.BR
import com.rockmvvm.rockbasemvvm.R
import com.rockmvvm.rockbasemvvm.databinding.ActivityHomeBinding
import com.rockmvvm.rockbasemvvm.ui.base.BaseActivity

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
    }


}
