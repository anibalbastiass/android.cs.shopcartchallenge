package com.anibalbastias.android.shopcart.data.dataStoreFactory.products.model

import com.anibalbastias.android.shopcart.data.dataStoreFactory.common.TypeData
import com.google.gson.annotations.SerializedName

data class ProductsData(

    @field:SerializedName("products")
    val products: List<ProductsItemData?>? = null
) : TypeData()