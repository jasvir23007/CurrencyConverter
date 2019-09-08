package com.rockmvvm.rockbasemvvm.ui.post

import com.rockmvvm.rockbasemvvm.R
import com.rockmvvm.rockbasemvvm.data.DataManager
import com.rockmvvm.rockbasemvvm.data.model.Post
import com.rockmvvm.rockbasemvvm.ui.base.BaseViewModel
import com.rockmvvm.rockbasemvvm.util.rx.SchedulerProvider

/**
 *
 * Created by jasvir on 2019-05-05
 *
 **/
class PostListViewModel(
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
        getCompositeDisposable().add(
            getDataManager()
                .doPostsApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnSubscribe { showLoading() }
                .doAfterTerminate { hideLoading() }
                .subscribe({ result -> onRetrievePostListSuccess(result as List<Post>) }, { onRetrievePostListError() })
        )
    }

    private fun hideLoading() {
        setIsLoading(false)
    }

    private fun showLoading() {
        setIsLoading(true)
    }

    private fun onRetrievePostListSuccess(postList: List<Post>) {
        //postListAdapter.updatePostList(postList)
    }

    private fun onRetrievePostListError() {
        errorMessage.value = R.string.post_error
    }
}