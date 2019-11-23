package com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.products

import android.os.Parcelable
import androidx.databinding.ObservableField
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.counters.CounterViewData
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductsItemViewData(
    val itemId: String? = null,
    val productAmountData: ProductAmountViewData? = null,
    val subtitle: String? = null,
    val imageUrl: String? = null,
    val title: String? = null,
    var counter: ObservableField<CounterViewData>? = ObservableField(CounterViewData(count = 0))
) : Parcelable