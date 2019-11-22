package com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.products

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductsItemViewData(
    val itemId: String? = null,
    val productAmountData: ProductAmountViewData? = null,
    val subtitle: String? = null,
    val imageUrl: String? = null,
    val title: String? = null
) : Parcelable