package com.sample.utils


import java.io.IOException


import retrofit2.Response

/**
 * Created by Kiran on 2019-11-16.
 */

object ErrorUtils {
    fun parseError(response: Response<*>): APIError {
        val converter = NetworkRequestor.retrofit()
            .responseBodyConverter<APIError>(APIError::class.java, arrayOfNulls(0))

        val error: APIError

        try {
            error = converter.convert(response.errorBody())!!
        } catch (e: IOException) {
            return APIError()
        }

        return error
    }
}

