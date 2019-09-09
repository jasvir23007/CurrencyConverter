package com.rockmvvm.rockbasemvvm.ui.currencyconverter

import com.google.gson.Gson
import com.rockmvvm.rockbasemvvm.R
import com.rockmvvm.rockbasemvvm.data.CurrencyListModel
import com.rockmvvm.rockbasemvvm.data.DataManager
import com.rockmvvm.rockbasemvvm.ui.base.BaseViewModel
import com.rockmvvm.rockbasemvvm.util.rx.SchedulerProvider
import org.json.JSONException
import org.json.JSONObject




/**
 *
 * Created by jasvir on 2019-05-05
 *
 **/
class CurrencyViewModel(
    mDataManager: DataManager,
    mSchedulerProvider: SchedulerProvider
) : BaseViewModel(mDataManager, mSchedulerProvider) {

    var listAllCurrencyData = ArrayList<CurrencyListModel>()
    var currencyAdapter = CurrencyAdapter()


    override fun retryClickListener() {
        loadPosts()
    }

    init {
        loadPosts()
    }


    private fun loadPosts() {
        val map = HashMap<String, String>()
        map.put("access_key", "a70973020b8650e5743e66eba2fd807b")
        getCompositeDisposable().add(
            getDataManager()
                .doApiCurrencyCall(map)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnSubscribe { showLoading() }
                .doAfterTerminate { hideLoading() }
                .subscribe(
                    { result -> onRetrievePostListSuccess(result) },
                    { onRetrievePostListError() })
        )
    }

    private fun hideLoading() {
        setIsLoading(false)
    }

    private fun showLoading() {
        setIsLoading(true)
    }

    private fun onRetrievePostListSuccess(postList: Any) {
        val jObj = JSONObject(Gson().toJson(postList))
        val json = jObj.getJSONObject("quotes")
        val iter = json.keys()
        while (iter.hasNext()) {
            val key = iter.next()
            try {
                val value = json.get(key)
                listAllCurrencyData.add(CurrencyListModel(key, value.toString()))
            } catch (e: JSONException) {
                // went wrong!
                e.printStackTrace()
            }

        }

        currencyAdapter.updateList(listAllCurrencyData)

    }

    private fun onRetrievePostListError() {
        errorMessage.value = R.string.post_error
    }

    fun onAmountChanged(text: CharSequence) {
        currencyAdapter.updateAmount(text.toString())
    }

}