package com.anibalbastias.android.shopcart.domain.products.repository

import com.anibalbastias.android.shopcart.data.dataStoreFactory.products.model.ProductsData
import io.reactivex.Flowable

/**
 * Created by anibalbastias on 3/19/18.
 */
interface IProductsRepository {

    fun getProducts(): Flowable<ProductsData>
}