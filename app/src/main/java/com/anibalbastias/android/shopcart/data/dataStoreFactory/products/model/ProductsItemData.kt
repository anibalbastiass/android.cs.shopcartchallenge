package com.anibalbastias.android.shopcart.data.dataStoreFactory.products.model

import com.google.gson.annotations.SerializedName

data class ProductsItemData(

	@field:SerializedName("itemId")
	val itemId: String? = null,

	@field:SerializedName("amount")
	val productAmountData: ProductAmountData? = null,

	@field:SerializedName("subtitle")
	val subtitle: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)