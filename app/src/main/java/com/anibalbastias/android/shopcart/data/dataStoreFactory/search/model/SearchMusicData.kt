package com.anibalbastias.android.shopcart.data.dataStoreFactory.search.model

import com.anibalbastias.android.shopcart.data.dataStoreFactory.common.TypeData
import com.anibalbastias.android.shopcart.data.dataStoreFactory.common.WrapperData
import com.google.gson.annotations.SerializedName

data class SearchMusicData(

	@field:SerializedName("resultCount")
	val resultCount: Int? = null,

	@field:SerializedName("results")
	val results: ArrayList<WrapperData?>? = null
) : TypeData()