package com.sample.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Kiran on 2019-11-16.
 */

internal object NetworkRequestor {
    private val BASE_URL = "https://api.github.com/"

    private val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    var retrofit = builder.build()

    private val httpClient = OkHttpClient.Builder()

    fun <S> createService(
            serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

    fun retrofit(): Retrofit {
        val client = httpClient.build()
        return builder.client(client).build()
    }
}
