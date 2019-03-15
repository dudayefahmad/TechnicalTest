package com.ahmaddudayef.technicaltest.data.network

import com.ahmaddudayef.technicaltest.data.network.model.ResponseData
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 13/03/19.
 */
class AppApiHelper @Inject constructor(
    private val apiService: ApiService
): ApiHelper {


    override fun getJokesRandom(): Flowable<ResponseData> {
        return apiService.getJokesRandom()
    }

}