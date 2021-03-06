package com.rockmvvm.rockbasemvvm.data

import android.content.Context
import com.rockmvvm.rockbasemvvm.data.local.db.AppDbHelper
import com.rockmvvm.rockbasemvvm.data.model.Post
import com.rockmvvm.rockbasemvvm.data.remote.ApiHelper
import io.reactivex.Observable
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * Created by jasvir on 2019-06-29
 *
 **/

@Singleton
class DataManagerImpl @Inject constructor(
    private val mDbHelper: AppDbHelper
    , private val mApiHelper: ApiHelper
) : DataManager {
    override fun saveData(data: String) {
        mDbHelper.saveData(data)
    }

    override fun getData(): String {
        return mDbHelper.getData()
    }

    override fun doApiCurrencyCall(map: HashMap<String, String>): Observable<Any> {
        return mApiHelper.doApiCurrencyCall(map)
    }

    override fun doPostsApiCall(): Observable<List<Post>> {
        return mApiHelper.doPostsApiCall()
    }

}