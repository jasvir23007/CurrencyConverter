package com.rockmvvm.rockbasemvvm.ui.currencyconverter

import com.rockmvvm.rockbasemvvm.R
import com.rockmvvm.rockbasemvvm.data.DataManager
import com.rockmvvm.rockbasemvvm.data.ResponseDTO
import com.rockmvvm.rockbasemvvm.data.model.Post
import com.rockmvvm.rockbasemvvm.ui.base.BaseViewModel
import com.rockmvvm.rockbasemvvm.util.rx.SchedulerProvider

/**
 *
 * Created by jasvir on 2019-05-05
 *
 **/
class CurrencyViewModel(
    mDataManager: DataManager,
    mSchedulerProvider: SchedulerProvider
) : BaseViewModel(mDataManager, mSchedulerProvider) {

    override fun retryClickListener() {
        loadPosts()
    }

    init {
        loadPosts()
    }


    private fun loadPosts() {
       val map = HashMap<String,String>()
        map.put("access_key","a70973020b8650e5743e66eba2fd807b")
        getCompositeDisposable().add(
            getDataManager()
                .doApiCurrencyCall(map)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnSubscribe { showLoading() }
                .doAfterTerminate { hideLoading() }
                .subscribe({ result -> onRetrievePostListSuccess(result as ResponseDTO) }, { onRetrievePostListError() })
        )
    }

    private fun hideLoading() {
        setIsLoading(false)
    }

    private fun showLoading() {
        setIsLoading(true)
    }

    private fun onRetrievePostListSuccess(postList: ResponseDTO) {
        //postListAdapter.updatePostList(postList)
    }

    private fun onRetrievePostListError() {
        errorMessage.value = R.string.post_error
    }
}