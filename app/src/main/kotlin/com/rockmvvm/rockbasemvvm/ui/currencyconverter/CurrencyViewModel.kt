package com.rockmvvm.rockbasemvvm.ui.currencyconverter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    val listCurrency = ArrayList<String>()

    val liveDataCurrency = MutableLiveData<ArrayList<String>>()


    override fun retryClickListener() {
        // loadPosts()
    }

    init {
        if (mDataManager.getData() != null)
            onRetrievePostListSuccess()

    }



    private fun hideLoading() {
        setIsLoading(false)
    }

    private fun showLoading() {
        setIsLoading(true)
    }

    fun onRetrievePostListSuccess() {

        if (mDataManager.getData() != null) {
            listCurrency.clear()
            val jObj = JSONObject((mDataManager.getData()))
            val json = jObj.getJSONObject("quotes")
            val iter = json.keys()
            while (iter.hasNext()) {
                val key = iter.next()
                try {
                    val value = json.get(key)
                    val fact = 1/value.toString().toDouble()
                    listAllCurrencyData.add(CurrencyListModel(key, value.toString(),fact))
                    if (key.equals("USDUSD"))
                        listCurrency.add("USD")
                    else
                    listCurrency.add(key.replace("USD",""))

                } catch (e: JSONException) {
                    // went wrong!
                    e.printStackTrace()
                }

            }
            currencyAdapter.updateList(listAllCurrencyData)
            liveDataCurrency.value = listCurrency
        }
    }

    private fun onRetrievePostListError() {
        errorMessage.value = R.string.post_error
    }

    fun onAmountChanged(text: CharSequence) {
        currencyAdapter.updateAmount(text.toString())
    }

}