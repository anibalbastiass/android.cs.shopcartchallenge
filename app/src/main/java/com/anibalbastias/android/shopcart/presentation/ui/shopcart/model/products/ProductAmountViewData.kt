package com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.products

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductAmountViewData(
    val currency: String? = null,
    val value: String? = null
) : Parcelable