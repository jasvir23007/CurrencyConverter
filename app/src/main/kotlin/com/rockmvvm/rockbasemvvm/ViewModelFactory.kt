package com.rockmvvm.rockbasemvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rockmvvm.rockbasemvvm.data.DataManager
import com.rockmvvm.rockbasemvvm.ui.currencyconverter.CurrencyViewModel
import com.rockmvvm.rockbasemvvm.ui.post.PostListViewModel
import com.rockmvvm.rockbasemvvm.util.rx.SchedulerProvider
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * Created by jasvir on 2019-05-05
 *
 **/

@Singleton
class ViewModelFactory
@Inject constructor(
    private val mDataManager: DataManager,
    private val mSchedulerProvider: SchedulerProvider
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PostListViewModel(mDataManager, mSchedulerProvider) as T
        }

        if (modelClass.isAssignableFrom(CurrencyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CurrencyViewModel(mDataManager, mSchedulerProvider) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class") as Throwable
    }
}