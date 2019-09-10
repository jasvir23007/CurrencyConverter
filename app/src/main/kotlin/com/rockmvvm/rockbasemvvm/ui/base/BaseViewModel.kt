package com.rockmvvm.rockbasemvvm.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableBoolean
import android.view.View
import com.rockmvvm.rockbasemvvm.data.DataManager
import com.rockmvvm.rockbasemvvm.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 *
 * Created by jasvir on 2019-05-05
 *
 **/
abstract class BaseViewModel(
    val mDataManager: DataManager,
    private val mSchedulerProvider: SchedulerProvider
) :
    ViewModel() {

    private var mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    private var mIsLoading: ObservableBoolean = ObservableBoolean()

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { retryClickListener() }


    override fun onCleared() {
        this.mCompositeDisposable.dispose()
        super.onCleared()
    }

    fun getCompositeDisposable(): CompositeDisposable = mCompositeDisposable


    fun getIsLoading(): ObservableBoolean = mIsLoading


    fun setIsLoading(isLoading: Boolean) {
        this.mIsLoading.set(isLoading)
    }

    fun getSchedulerProvider(): SchedulerProvider = mSchedulerProvider


    fun getDataManager(): DataManager = mDataManager


    abstract fun retryClickListener()
}