package com.rockmvvm.rockbasemvvm.data.remote

import com.rockmvvm.rockbasemvvm.data.ResponseDTO
import com.rockmvvm.rockbasemvvm.data.model.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 *
 * Created by jasvir on 2019-05-05
 *
 **/
interface ApiInterface {

    @GET(ENDPOINT_POSTS)
    fun getPosts(): Observable<List<Post>>


    @GET(ENDPOINT_CURRENCY)
    fun getCurrencyExchangeRates(@QueryMap map: HashMap<String,String>): Observable<ResponseDTO>

}