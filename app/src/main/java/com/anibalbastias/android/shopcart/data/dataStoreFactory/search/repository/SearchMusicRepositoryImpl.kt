package com.anibalbastias.android.shopcart.data.dataStoreFactory.search.repository

import com.anibalbastias.android.shopcart.R
import com.anibalbastias.android.shopcart.data.dataStoreFactory.search.model.SearchMusicData
import com.anibalbastias.android.shopcart.data.shopcart.ShopCartApiService
import com.anibalbastias.android.shopcart.domain.search.repository.ISeriesRepository
import com.anibalbastias.android.shopcart.presentation.context
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by anibalbastias on 19-03-18.
 */
@Singleton
class SearchMusicRepositoryImpl @Inject constructor(private val shopCartApiService: ShopCartApiService) : ISeriesRepository {

    override fun searchMusic(url: String): Flowable<SearchMusicData> =
        shopCartApiService.searchMusic(context?.getString(R.string.shopcart_endpoint) + url)

}