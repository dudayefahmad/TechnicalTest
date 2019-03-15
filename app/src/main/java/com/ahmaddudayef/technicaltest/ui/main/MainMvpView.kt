package com.ahmaddudayef.technicaltest.ui.main

import com.ahmaddudayef.technicaltest.data.network.model.ResponseData
import com.ahmaddudayef.technicaltest.data.network.model.Value
import com.ahmaddudayef.technicaltest.ui.base.MvpView

/**
 * Created by Ahmad Dudayef on 13/03/19.
 */
interface MainMvpView : MvpView {
    fun updateJokesRandom(listData: MutableList<Value>)
}