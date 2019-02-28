package com.alejofila.newsdemo.common.uimodel

import android.os.Parcel
import android.os.Parcelable

data class NewsUiModel(val title:String,val description:String?,val image:String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString())


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsUiModel> {
        override fun createFromParcel(parcel: Parcel): NewsUiModel {
            return NewsUiModel(parcel)
        }

        override fun newArray(size: Int): Array<NewsUiModel?> {
            return arrayOfNulls(size)
        }
    }
}

