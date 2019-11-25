package com.anibalbastias.android.shopcart.presentation.ui.shopcart.mapper.counters

import com.anibalbastias.android.shopcart.data.dataStoreFactory.counters.model.CounterData
import com.anibalbastias.android.shopcart.domain.base.Mapper
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.counters.CounterViewData
import javax.inject.Inject

/**
 * Created by anibalbastias on 2019-11-25.
 */

open class CounterViewDataMapper @Inject constructor() : Mapper<CounterViewData?, CounterData?> {

    override fun executeMapping(type: CounterData?): CounterViewData? {
        return type?.let { item ->
            CounterViewData(
                id = item.id,
                title = item.title,
                count = item.count
            )
        }
    }
}