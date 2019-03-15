package com.ahmaddudayef.technicaltest.data.network

import com.ahmaddudayef.technicaltest.data.network.model.ResponseData
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ahmad Dudayef on 13/03/19.
 */
interface ApiService {
    companion object {
        const val ENDPOINT = "jokes/random/10-"
    }

    @GET(ENDPOINT)
    fun getJokesRandom(): Flowable<ResponseData>
}