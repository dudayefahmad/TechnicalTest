package com.ahmaddudayef.technicaltest.ui.base

/**
 * Created by Ahmad Dudayef on 13/03/19.
 */
interface MvpView {
    fun showLoading()

    fun hideLoading()

    fun showMessage(message: String)

    fun showError(message: String)
}