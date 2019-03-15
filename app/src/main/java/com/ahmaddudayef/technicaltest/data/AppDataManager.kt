package com.ahmaddudayef.technicaltest.data

import com.ahmaddudayef.technicaltest.data.network.AppApiHelper
import com.ahmaddudayef.technicaltest.data.network.model.ResponseData
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 13/03/19.
 */
class AppDataManager @Inject constructor(
    private val appApiHelper: AppApiHelper
): DataManager {

    override fun getJokesRandom(): Flowable<ResponseData> {
        return appApiHelper.getJokesRandom()
    }

}