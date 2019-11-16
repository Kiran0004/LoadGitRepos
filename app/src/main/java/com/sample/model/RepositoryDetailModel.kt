package com.sample.model


import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Kiran on 2019-11-16.
 */



class RepositoryDetailModel(`in`: Parcel) : Parcelable {

    @SerializedName("owner")
    @Expose
    val owner: Owner? = null

    @SerializedName("description")
    @Expose
     val mName: String

    @SerializedName("name")
    @Expose
     val mDescription: String

    @SerializedName("stargazers_count")
    @Expose
     var mStarsNumber: Float = 0.toFloat()

    init {
        mDescription = `in`.readString()
        mName = `in`.readString()
        mStarsNumber = `in`.readFloat()
    }

    inner class Owner {
        @SerializedName("login")
        var login: String? = null
        @SerializedName("avatar_url")
        val avatar_url: String? = null
    }

    override  fun describeContents(): Int {
        return 0
    }

   override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(mDescription)
        parcel.writeString(mName)
        parcel.writeFloat(mStarsNumber)
    }


    companion object CREATOR : Parcelable.Creator<RepositoryDetailModel> {
        override fun createFromParcel(parcel: Parcel): RepositoryDetailModel {
            return RepositoryDetailModel(parcel)
        }

        override fun newArray(size: Int): Array<RepositoryDetailModel?> {
            return arrayOfNulls(size)
        }
    }
}

