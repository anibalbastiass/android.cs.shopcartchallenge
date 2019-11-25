package com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.products

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by anibalbastias on 2019-11-25.
 */

@Parcelize
data class ProductsViewData(
    val products: List<ProductsItemViewData?>? = null
) : Parcelable