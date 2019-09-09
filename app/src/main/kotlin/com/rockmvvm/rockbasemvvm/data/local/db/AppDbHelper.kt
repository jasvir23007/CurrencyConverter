package com.rockmvvm.rockbasemvvm.data.local.db

import com.rockmvvm.rockbasemvvm.data.model.Post
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * Created by jasvir on 2019-06-29
 *
 **/

@Singleton
class AppDbHelper @Inject constructor(private val mAppDatabase: AppDatabase) : DbHelper {
    override fun getData(): String {
        return mAppDatabase.postDao().data
    }


    override fun saveData(data: String): Observable<Boolean> = Observable.fromCallable {
        if (mAppDatabase.postDao().insert(Post(json = data)) == -1L) {
            mAppDatabase.postDao().update(Post(json = data))
        }
        true
    }


}