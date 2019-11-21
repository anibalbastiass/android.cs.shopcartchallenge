package com.anibalbastias.android.shopcart.domain.counters.repository

import com.anibalbastias.android.shopcart.data.dataStoreFactory.counters.model.CounterData
import io.reactivex.Flowable

/**
 * Created by anibalbastias on 3/19/18.
 */
interface ICountersRepository {
    fun getCounters(): Flowable<List<CounterData>>
    fun postCounter(): Flowable<List<CounterData>>
    fun postIncCounter(): Flowable<List<CounterData>>
    fun postDecCounter(): Flowable<List<CounterData>>
    fun deleteCounter(): Flowable<List<CounterData>>
}