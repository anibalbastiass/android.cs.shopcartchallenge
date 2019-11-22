package com.anibalbastias.android.shopcart.presentation.ui.shopcart.mapper.products

import com.anibalbastias.android.shopcart.data.dataStoreFactory.products.model.ProductAmountData
import com.anibalbastias.android.shopcart.data.dataStoreFactory.products.model.ProductsItemData
import com.anibalbastias.android.shopcart.domain.base.Mapper
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.products.ProductAmountViewData
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.products.ProductsItemViewData
import javax.inject.Inject

/**
 * Created by anibalbastias on 2019-09-07.
 */

open class ProductsAmountViewDataMapper @Inject constructor() : Mapper<ProductAmountViewData?, ProductAmountData?> {

    override fun executeMapping(type: ProductAmountData?): ProductAmountViewData? {
        return type?.let {  item ->
            ProductAmountViewData(
                currency = item.currency,
                value = item.value
            )
        }
    }
}