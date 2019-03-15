package com.ahmaddudayef.technicaltest.ui.base

/**
 * Created by Ahmad Dudayef on 13/03/19.
 */
interface MvpPresenter<V : MvpView> {

    fun onAttach(mvpView: V)

    fun onDetach()
}