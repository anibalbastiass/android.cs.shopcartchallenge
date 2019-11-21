package com.anibalbastias.android.shopcart.data.shopcart

import com.anibalbastias.android.shopcart.data.dataStoreFactory.counters.model.CounterData
import com.anibalbastias.android.shopcart.data.dataStoreFactory.products.model.ProductsData
import com.anibalbastias.android.shopcart.data.dataStoreFactory.search.model.SearchMusicData
import com.anibalbastias.android.shopcart.data.shopcart.ShopCartApiConstants.DELETE_COUNTER
import com.anibalbastias.android.shopcart.data.shopcart.ShopCartApiConstants.GET_COUNTERS
import com.anibalbastias.android.shopcart.data.shopcart.ShopCartApiConstants.GET_PRODUCTS
import com.anibalbastias.android.shopcart.data.shopcart.ShopCartApiConstants.POST_COUNTER
import com.anibalbastias.android.shopcart.data.shopcart.ShopCartApiConstants.POST_COUNTER_DEC
import com.anibalbastias.android.shopcart.data.shopcart.ShopCartApiConstants.POST_COUNTER_INC
import io.reactivex.Flowable
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * Created by anibalbastias on 3/03/19.
 */

interface ShopCartApiService {

    // Dummy
    @GET
    fun searchMusic(@Url nextUrl: String): Flowable<SearchMusicData>

    //region Products
    @GET(GET_PRODUCTS)
    fun getProducts(): Flowable<ProductsData>
    //endregion

    //region Counters
    @GET(GET_COUNTERS)
    fun getCounters(): Flowable<List<CounterData>>

    @POST(POST_COUNTER)
    fun postCounter(): Flowable<List<CounterData>>

    @POST(POST_COUNTER_INC)
    fun postIncCounter(): Flowable<List<CounterData>>

    @POST(POST_COUNTER_DEC)
    fun postDecCounter(): Flowable<List<CounterData>>

    @DELETE(DELETE_COUNTER)
    fun deleteCounter(): Flowable<List<CounterData>>
    //endregion
}