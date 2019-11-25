package com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.counters

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by anibalbastias on 2019-11-25.
 */

@Parcelize
data class CounterViewData(
    var id: String? = null,
    var title: String? = null,
    var count: Int? = 0
) : Parcelable