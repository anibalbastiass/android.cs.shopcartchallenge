package com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.counters

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CounterViewData(
    var id: String? = null,
    var title: String? = null,
    var count: Int? = null
) : Parcelable