package com.ahmaddudayef.technicaltest.data.network

import com.ahmaddudayef.technicaltest.data.network.model.ResponseData
import io.reactivex.Flowable

/**
 * Created by Ahmad Dudayef on 13/03/19.
 */
interface ApiHelper {
    fun getJokesRandom(): Flowable<ResponseData>
}