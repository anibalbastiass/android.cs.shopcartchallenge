package com.anibalbastias.android.shopcart.data.dataStoreFactory.counters.model

import com.anibalbastias.android.shopcart.data.dataStoreFactory.common.TypeData
import com.google.gson.annotations.SerializedName

data class CounterData(

    @field:SerializedName("id")
    var id: String? = null,

    @field:SerializedName("title")
    var title: String? = null,

    @field:SerializedName("count")
    var count: Int? = null
) : TypeData()