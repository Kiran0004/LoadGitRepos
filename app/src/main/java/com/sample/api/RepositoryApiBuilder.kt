package com.sample.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Kiran on 2019-11-16.
 */

class RepositoryApiBuilder {

    private val retrofit: Retrofit

    val service: RepositoryApiService
        get() = retrofit.create(RepositoryApiService::class.java)

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    companion object {

        val BASE_URL = "https://api.github.com"
    }
}
