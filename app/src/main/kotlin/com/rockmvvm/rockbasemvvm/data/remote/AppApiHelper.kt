package com.rockmvvm.rockbasemvvm.data.remote

import com.rockmvvm.rockbasemvvm.data.model.Post
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
class AppApiHelper @Inject constructor(private val mApiInterface: ApiInterface) : ApiHelper {
    override fun doApiCurrencyCall(map: HashMap<String, String>): Observable<Any> {
        return mApiInterface.getCurrencyExchangeRates(map)

    }

    override fun doPostsApiCall(): Observable<List<Post>> {
        return mApiInterface.getPosts()
    }



}