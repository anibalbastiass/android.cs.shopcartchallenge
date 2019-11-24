package com.anibalbastias.android.shopcart.data.shopcart

import com.anibalbastias.android.shopcart.data.dataStoreFactory.counters.model.CounterData
import com.anibalbastias.android.shopcart.data.dataStoreFactory.products.model.ProductsData
import com.anibalbastias.android.shopcart.data.shopcart.ShopCartApiConstants.DELETE_COUNTER
import com.anibalbastias.android.shopcart.data.shopcart.ShopCartApiConstants.GET_COUNTERS
import com.anibalbastias.android.shopcart.data.shopcart.ShopCartApiConstants.GET_PRODUCTS
import com.anibalbastias.android.shopcart.data.shopcart.ShopCartApiConstants.POST_COUNTER
import com.anibalbastias.android.shopcart.data.shopcart.ShopCartApiConstants.POST_COUNTER_DEC
import com.anibalbastias.android.shopcart.data.shopcart.ShopCartApiConstants.POST_COUNTER_INC
import io.reactivex.Flowable
import retrofit2.http.*

/**
 * Created by anibalbastias on 3/03/19.
 */

interface ShopCartApiService {

    //region Products
    @GET(GET_PRODUCTS)
    fun getProducts(): Flowable<ProductsData>
    //endregion

    //region Counters
    @GET(GET_COUNTERS)
    fun getCounters(): Flowable<List<CounterData?>>

    @POST(POST_COUNTER)
    fun postCounter(@Body request: CounterData): Flowable<List<CounterData?>>

    @POST(POST_COUNTER_INC)
    fun postIncCounter(@Body request: CounterData): Flowable<List<CounterData?>>

    @POST(POST_COUNTER_DEC)
    fun postDecCounter(@Body request: CounterData): Flowable<List<CounterData?>>

    @HTTP(method = "DELETE", path = DELETE_COUNTER, hasBody = true)
    fun deleteCounter(@Body request: CounterData): Flowable<List<CounterData?>>
    //endregion
}