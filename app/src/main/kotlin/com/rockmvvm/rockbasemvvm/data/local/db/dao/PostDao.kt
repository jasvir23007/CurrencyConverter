package com.rockmvvm.rockbasemvvm.data.local.db.dao

import android.arch.persistence.room.*
import com.rockmvvm.rockbasemvvm.data.model.Post

/**
 *
 * Created by jasvir on 2019-06-29
 *
 **/

@Dao
interface PostDao {

//    @Insert
//    fun inserAll(vararg post: Post)
//
//    @get:Query("SELECT * FROM post")
//    val allPosts: List<Post>

    @Insert
    fun update(vararg post: Post)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(token: Post): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(token: Post)

    @get:Query("SELECT json FROM Post")
    val data: String

    @Query("DELETE FROM Post")
    fun deleteToken()



}