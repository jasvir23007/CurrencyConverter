package com.rockmvvm.rockbasemvvm.data.local.db

import io.reactivex.Observable

/**
 *
 * Created by jasvir on 2019-06-29
 *
 **/
interface DbHelper {

    fun saveData(data: String)

    fun getData(): String

}