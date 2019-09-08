package com.rockmvvm.rockbasemvvm.data.local.db

import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * Created by jasvir on 2019-06-29
 *
 **/

@Singleton
class AppDbHelper @Inject constructor(private val mAppDatabase: AppDatabase) : DbHelper {


}