package com.rockmvvm.rockbasemvvm.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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

