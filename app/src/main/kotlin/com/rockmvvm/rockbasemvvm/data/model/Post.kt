package com.rockmvvm.rockbasemvvm.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 *
 * Created by jasvir on 2019-06-29
 *
 **/

@Entity
data class Post(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 1,
    @ColumnInfo(name = "json")
    val json: String
)

