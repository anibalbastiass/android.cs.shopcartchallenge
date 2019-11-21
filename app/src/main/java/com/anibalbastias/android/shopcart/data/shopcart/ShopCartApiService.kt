package com.anibalbastias.android.shopcart.data.shopcart

import com.anibalbastias.android.shopcart.data.dataStoreFactory.counters.CounterData
import com.anibalbastias.android.shopcart.data.dataStoreFactory.search.model.SearchMusicData
import io.reactivex.Flowable
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * Created by anibalbastias on 3/03/19.
 */

interface ShopCartApiService {

    //Get Search Data
    @GET
    fun searchMusic(@Url nextUrl: String): Flowable<SearchMusicData>

    @GET("api/v1/products")
    fun getProducts(): Flowable<List<CounterData>>

    @GET("api/v1/counters")
    fun getCounters(): Flowable<List<CounterData>>

    @POST("api/v1/counter")
    fun postCounter(): Flowable<List<CounterData>>

    @POST("api/v1/counter/inc")
    fun postIncCounter(): Flowable<List<CounterData>>

    @POST("api/v1/counter/dec")
    fun postDecCounter(): Flowable<List<CounterData>>

    @DELETE("api/v1/counter")
    fun deleteCounter(): Flowable<List<CounterData>>
}