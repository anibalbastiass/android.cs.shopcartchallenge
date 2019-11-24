package com.anibalbastias.android.shopcart.presentation.ui.shopcart.mapper.products

import com.anibalbastias.android.shopcart.data.dataStoreFactory.products.model.ProductsData
import com.anibalbastias.android.shopcart.domain.base.Mapper
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.products.ProductsViewData
import javax.inject.Inject

/**
 * Created by anibalbastias on 2019-09-07.
 */

open class ProductsViewDataMapper @Inject constructor(
    private val productsItemViewDataMapper: ProductsItemViewDataMapper
) : Mapper<ProductsViewData?, ProductsData?> {

    override fun executeMapping(type: ProductsData?): ProductsViewData? {
        return type?.let { item ->
            ProductsViewData(
                products = item.products?.map { productsItemViewDataMapper.executeMapping(it) }
            )
        }
    }
}