package com.anibalbastias.android.shopcart.domain.counters.repository

import com.anibalbastias.android.shopcart.data.dataStoreFactory.counters.model.CounterData
import io.reactivex.Flowable

/**
 * Created by anibalbastias on 3/19/18.
 */
interface ICountersRepository {
    fun getCounters(): Flowable<List<CounterData>>
    fun postCounter(request: CounterData): Flowable<List<CounterData>>
    fun postIncCounter(request: CounterData): Flowable<List<CounterData>>
    fun postDecCounter(request: CounterData): Flowable<List<CounterData>>
    fun deleteCounter(request: CounterData): Flowable<List<CounterData>>
}