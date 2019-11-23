package com.anibalbastias.android.shopcart.presentation.ui.shopcart.mapper.counters

import com.anibalbastias.android.shopcart.data.dataStoreFactory.counters.model.CounterData
import com.anibalbastias.android.shopcart.domain.base.Mapper
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.counters.CounterViewData
import javax.inject.Inject

/**
 * Created by anibalbastias on 2019-09-07.
 */

open class CounterListViewDataMapper @Inject constructor(
    private val counterViewDataMapper: CounterViewDataMapper
) : Mapper<List<CounterViewData?>?, List<CounterData?>?> {

    override fun executeMapping(type: List<CounterData?>?): List<CounterViewData?>? {
        val list = mutableListOf<CounterViewData?>()
        type?.map {
            list.add(counterViewDataMapper.executeMapping(it))
        }
        return list
    }
}