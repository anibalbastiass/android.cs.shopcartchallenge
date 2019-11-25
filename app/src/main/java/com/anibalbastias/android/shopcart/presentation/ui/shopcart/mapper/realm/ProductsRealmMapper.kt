package com.anibalbastias.android.shopcart.presentation.ui.shopcart.mapper.realm

import com.anibalbastias.android.shopcart.domain.base.Mapper
import com.anibalbastias.android.shopcart.domain.counters.model.CounterEntity
import com.anibalbastias.android.shopcart.domain.products.model.ProductsItemEntity
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.counters.CounterViewData
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.products.ProductAmountViewData
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.products.ProductsItemViewData
import javax.inject.Inject

/**
 * Created by anibalbastias on 2019-11-25.
 */

class ProductsRealmMapper @Inject constructor() :
    Mapper<ProductsItemEntity?, ProductsItemViewData?> {

    override fun executeMapping(type: ProductsItemViewData?): ProductsItemEntity? {
        return type?.let { item ->
            val entity = ProductsItemEntity()
            entity.itemId = item.itemId
            entity.title = item.title
            entity.subtitle = item.subtitle
            entity.amount = "${item.productAmountData?.currency} ${item.productAmountData?.value}"
            entity.imageUrl = item.imageUrl

            // Counter
            val counterEntity = CounterEntity()
            counterEntity.id = item.counter?.get()?.id
            counterEntity.title = item.counter?.get()?.title
            counterEntity.count = item.counter?.get()?.count
            entity.counter = counterEntity

            entity
        }
    }

    fun inverseExecuteMapping(type: ProductsItemEntity?): ProductsItemViewData? {
        return type?.let { item ->
            val viewData = ProductsItemViewData()
            viewData.itemId = item.itemId
            viewData.title = item.title
            viewData.subtitle = item.subtitle
            viewData.imageUrl = item.imageUrl

            val productAmountData = ProductAmountViewData()
            productAmountData.currency = item.amount?.split(" ")?.get(0)
            productAmountData.value = item.amount?.split(" ")?.get(1)
            viewData.productAmountData = productAmountData

            // Counter
            val counterViewData = CounterViewData()
            counterViewData.id = item.counter?.id
            counterViewData.title = item.counter?.title
            counterViewData.count = item.counter?.count
            viewData.counter?.set(counterViewData)

            viewData
        }
    }
}