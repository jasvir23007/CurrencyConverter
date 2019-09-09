package com.rockmvvm.rockbasemvvm.data.remote

import com.rockmvvm.rockbasemvvm.data.model.Post
import io.reactivex.Observable
import org.json.JSONObject

/**
 *
 * Created by jasvir on 2019-06-29
 *
 **/
interface ApiHelper {

    fun doPostsApiCall(): Observable<List<Post>>

    fun doApiCurrencyCall(map: HashMap<String, String>): Observable<Any>


}