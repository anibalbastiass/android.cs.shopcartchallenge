package com.anibalbastias.android.shopcart.data.dataStoreFactory.search.model

import com.anibalbastias.android.shopcart.data.dataStoreFactory.common.WrapperData
import com.google.gson.annotations.SerializedName

data class CollectionResultItemData(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("copyright")
	val copyright: String? = null,

	@field:SerializedName("amgArtistId")
	val amgArtistId: Int? = null,

	@field:SerializedName("collectionType")
	val collectionType: String? = null,

	@field:SerializedName("collectionExplicitness")
	val collectionExplicitness: String? = null,

	@field:SerializedName("currency")
	val currency: String? = null

) : WrapperData()