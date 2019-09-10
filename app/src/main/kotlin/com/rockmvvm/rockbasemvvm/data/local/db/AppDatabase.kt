package com.rockmvvm.rockbasemvvm.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rockmvvm.rockbasemvvm.data.local.db.dao.PostDao
import com.rockmvvm.rockbasemvvm.data.model.Post

/**
 *
 * Created by jasvir on 2019-05-05
 *
 **/
@Database(entities = [Post::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao
}