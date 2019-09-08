package com.rockmvvm.rockbasemvvm.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.rockmvvm.rockbasemvvm.ViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 *
 * Created by jasvir on 2019-06-29
 *
 **/
abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    private lateinit var mViewDataBinding: T
    protected var mViewModel: V? = null

    @Inject
    protected lateinit var factory: ViewModelFactory

    abstract fun getBindingVariable(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    fun getViewDataBinding(): T = mViewDataBinding


    private fun performDataBinding() {
        if (mViewModel == null) {
            mViewModel = getViewModel()
        }
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.executePendingBindings()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        performDataBinding()
    }
}