package com.anibalbastias.android.shopcart.presentation.ui.shopcart.mapper.products

import com.anibalbastias.android.shopcart.data.dataStoreFactory.products.model.ProductsItemData
import com.anibalbastias.android.shopcart.domain.base.Mapper
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.products.ProductsItemViewData
import javax.inject.Inject

/**
 * Created by anibalbastias on 2019-09-07.
 */

open class ProductsItemViewDataMapper @Inject constructor(
    private val productsAmountViewDataMapper: ProductsAmountViewDataMapper
) : Mapper<ProductsItemViewData?, ProductsItemData?> {

    override fun executeMapping(type: ProductsItemData?): ProductsItemViewData? {
        return type?.let { item ->
            ProductsItemViewData(
                itemId = item.itemId,
                title = item.title,
                subtitle = item.subtitle,
                imageUrl = item.imageUrl,
                productAmountData = item.productAmountData?.let {
                    productsAmountViewDataMapper.executeMapping(
                        it
                    )
                }
            )
        }
    }
}