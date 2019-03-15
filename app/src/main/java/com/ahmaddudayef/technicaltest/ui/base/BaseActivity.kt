package com.ahmaddudayef.technicaltest.ui.base

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.ahmaddudayef.technicaltest.utils.CommonUtils

/**
 * Created by Ahmad Dudayef on 13/03/19.
 */
open class BaseActivity : AppCompatActivity(), MvpView {

    private var progressDialog: Dialog? = null

    private fun displayMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun displayError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressDialog = CommonUtils.showLoadingDialog(this)
    }

    override fun hideLoading() {
        if (progressDialog != null && progressDialog!!.isShowing()) {
            progressDialog!!.cancel()
        }
    }

    override fun showMessage(message: String) {
        displayMessage(message)
    }

    override fun showError(message: String) {
        displayError(message)
    }

}