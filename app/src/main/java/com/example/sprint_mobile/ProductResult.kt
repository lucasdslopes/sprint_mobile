package com.example.sprint_mobile

import android.os.Parcel
import android.os.Parcelable

data class ProductResult(
    val title: String,
    val price: String,
    val link: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(price)
        parcel.writeString(link)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductResult> {
        override fun createFromParcel(parcel: Parcel): ProductResult {
            return ProductResult(parcel)
        }

        override fun newArray(size: Int): Array<ProductResult?> {
            return arrayOfNulls(size)
        }
    }
}
