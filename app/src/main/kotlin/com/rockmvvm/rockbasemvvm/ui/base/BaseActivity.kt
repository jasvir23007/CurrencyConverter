package com.rockmvvm.rockbasemvvm.ui.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.rockmvvm.rockbasemvvm.MyApplication
import com.rockmvvm.rockbasemvvm.ViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 *
 * Created by jasvir on 2019-06-29
 *
 **/
abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    public lateinit var mViewDataBinding: T
    protected var mViewModel: V? = null

    @Inject
    protected lateinit var factory: ViewModelFactory

    abstract fun getBindingVariable(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    fun getViewDataBinding(): T = mViewDataBinding

    var myApp: MyApplication? = null


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
        myApp = applicationContext as MyApplication
    }

    override fun onResume() {
        super.onResume()
        myApp?.setCurrentActivity(this)

    }


}