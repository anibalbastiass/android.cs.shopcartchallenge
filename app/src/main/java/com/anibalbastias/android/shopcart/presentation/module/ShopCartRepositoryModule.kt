package com.anibalbastias.android.shopcart.presentation.module

import com.anibalbastias.android.shopcart.data.dataStoreFactory.search.repository.SearchMusicRepositoryImpl
import com.anibalbastias.android.shopcart.domain.search.repository.ISeriesRepository
import dagger.Binds
import dagger.Module

@Module
abstract class ShopCartRepositoryModule {

    @Binds
    abstract fun bindSeriesDataRepository(repository: SearchMusicRepositoryImpl): ISeriesRepository

}