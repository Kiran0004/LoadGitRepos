package com.sample.model


import com.google.gson.annotations.SerializedName

/**
 * Created by Kiran on 2019-11-16.
 */

class RepositoryResponse {

    @SerializedName("items")
    val items: MutableList<RepositoryDetailModel>? = null

}
