package com.rockmvvm.rockbasemvvm.di.builder

import com.rockmvvm.rockbasemvvm.ui.post.PostListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 *
 * Created by jasvir on 2019-06-29
 *
 **/

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindPostListActivity(): PostListActivity
}