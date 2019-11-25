package com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.products

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductAmountViewData(
    var currency: String? = null,
    var value: String? = null
) : Parcelable