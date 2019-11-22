package com.anibalbastias.android.shopcart.presentation.ui.shopcart.interfaces

import android.view.View
import com.anibalbastias.android.shopcart.data.dataStoreFactory.counters.model.CounterData
import com.anibalbastias.android.shopcart.presentation.util.adapter.base.BaseBindClickHandler

/**
 * Created by anibalbastias on 2019-08-13.
 */

interface ShopCartItemListener<T> : BaseBindClickHandler<T> {

    override fun onClickView(view: View, item: T)
    fun onAddCounterItem(item: CounterData)
    fun onIncCounterItem(item: CounterData)
    fun onDecCounterItem(item: CounterData)
    fun onDeleteCounterItem(item: CounterData)
}