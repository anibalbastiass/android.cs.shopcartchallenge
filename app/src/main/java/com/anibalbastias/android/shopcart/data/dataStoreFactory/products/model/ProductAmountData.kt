package com.anibalbastias.android.shopcart.data.dataStoreFactory.products.model

import com.anibalbastias.android.shopcart.data.dataStoreFactory.common.TypeData
import com.google.gson.annotations.SerializedName

data class ProductAmountData(

    @field:SerializedName("currency")
    val currency: String? = null,

    @field:SerializedName("value")
    val value: String? = null

) : TypeData()