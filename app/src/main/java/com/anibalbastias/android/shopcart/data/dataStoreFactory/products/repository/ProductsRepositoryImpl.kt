package com.anibalbastias.android.shopcart.data.dataStoreFactory.products.repository

import com.anibalbastias.android.shopcart.data.dataStoreFactory.products.model.ProductsData
import com.anibalbastias.android.shopcart.data.shopcart.ShopCartApiService
import com.anibalbastias.android.shopcart.domain.products.repository.IProductsRepository
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by anibalbastias on 21-11-19.
 */

@Singleton
class ProductsRepositoryImpl @Inject constructor(private val shopCartApiService: ShopCartApiService) : IProductsRepository {

    override fun getProducts(): Flowable<ProductsData> = shopCartApiService.getProducts()
}