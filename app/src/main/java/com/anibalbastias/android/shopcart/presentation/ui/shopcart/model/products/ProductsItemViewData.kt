package com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.products

import android.os.Parcelable
import androidx.databinding.ObservableField
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.counters.CounterViewData
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductsItemViewData(
    var itemId: String? = null,
    var productAmountData: ProductAmountViewData? = null,
    var subtitle: String? = null,
    var imageUrl: String? = null,
    var title: String? = null,
    var counter: ObservableField<CounterViewData>? = ObservableField(CounterViewData(count = 0)),
    var isUpdating: Boolean? = false
) : Parcelable