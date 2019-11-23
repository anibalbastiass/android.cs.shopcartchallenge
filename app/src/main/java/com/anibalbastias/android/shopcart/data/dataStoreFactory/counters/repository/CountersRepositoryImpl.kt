package com.anibalbastias.android.shopcart.data.dataStoreFactory.counters.repository

import com.anibalbastias.android.shopcart.data.dataStoreFactory.counters.model.CounterData
import com.anibalbastias.android.shopcart.data.shopcart.ShopCartApiService
import com.anibalbastias.android.shopcart.domain.counters.repository.ICountersRepository
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by anibalbastias on 21-11-19.
 */

@Singleton
class CountersRepositoryImpl @Inject constructor(private val shopCartApiService: ShopCartApiService) :
    ICountersRepository {

    override fun getCounters(): Flowable<List<CounterData?>> =
        shopCartApiService.getCounters()

    override fun postCounter(request: CounterData): Flowable<List<CounterData?>> =
        shopCartApiService.postCounter(request)

    override fun postIncCounter(request: CounterData): Flowable<List<CounterData?>> =
        shopCartApiService.postIncCounter(request)

    override fun postDecCounter(request: CounterData): Flowable<List<CounterData?>> =
        shopCartApiService.postDecCounter(request)

    override fun deleteCounter(request: CounterData): Flowable<List<CounterData?>> =
        shopCartApiService.deleteCounter(request)
}