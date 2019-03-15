package com.ahmaddudayef.technicaltest.ui.main

import com.ahmaddudayef.technicaltest.ui.base.MvpPresenter

/**
 * Created by Ahmad Dudayef on 13/03/19.
 */
interface MainMvpPresenter<V: MainMvpView> : MvpPresenter<V> {
    fun getJokesRandom()
}