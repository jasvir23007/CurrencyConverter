package com.rockmvvm.rockbasemvvm.ui.post

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import com.rockmvvm.rockbasemvvm.BR
import com.rockmvvm.rockbasemvvm.R
import com.rockmvvm.rockbasemvvm.databinding.ActivityPostListBinding
import com.rockmvvm.rockbasemvvm.ui.base.BaseActivity

class PostListActivity : BaseActivity<ActivityPostListBinding, PostListViewModel>() {

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_post_list

    override fun getViewModel(): PostListViewModel {
        mViewModel = ViewModelProviders.of(this, factory).get(PostListViewModel::class.java)
        return mViewModel as PostListViewModel
    }

    private lateinit var mSnackBar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel?.errorMessage?.observe(
            this,
            Observer { errorMessage ->
                errorMessage?.let {
                    showError(it)
                } ?: kotlin.run { hideError() }
            })

    }

    private fun hideError() {
        mSnackBar.dismiss()
    }

    private fun showError(@StringRes error: Int) {
        /*mSnackBar = Snackbar.make(this.root, error, Snackbar.LENGTH_INDEFINITE).also {
            it.setAction("Retry", mViewModel.errorClickListener)
            it.show()
        }*/
    }
}
