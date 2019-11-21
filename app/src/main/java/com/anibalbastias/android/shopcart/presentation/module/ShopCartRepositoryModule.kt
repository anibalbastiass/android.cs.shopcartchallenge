package com.anibalbastias.android.shopcart.presentation.module

import com.anibalbastias.android.shopcart.data.dataStoreFactory.counters.repository.CountersRepositoryImpl
import com.anibalbastias.android.shopcart.data.dataStoreFactory.products.repository.ProductsRepositoryImpl
import com.anibalbastias.android.shopcart.domain.counters.repository.ICountersRepository
import com.anibalbastias.android.shopcart.domain.products.repository.IProductsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class ShopCartRepositoryModule {

    @Binds
    abstract fun bindProductsDataRepository(repository: ProductsRepositoryImpl): IProductsRepository

    @Binds
    abstract fun bindCountersDataRepository(repository: CountersRepositoryImpl): ICountersRepository

}