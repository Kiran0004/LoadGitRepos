package com.sample.api


import com.sample.model.RepositoryResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by Kiran on 2019-11-16.
 */

interface RepositoryApiService {

    @GET("/search/repositories")
    fun getRepositoryList(@QueryMap(encoded = false) filter: Map<String, String>): Call<RepositoryResponse>


}
