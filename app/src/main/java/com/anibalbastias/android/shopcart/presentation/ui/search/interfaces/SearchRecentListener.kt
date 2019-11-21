package com.anibalbastias.android.shopcart.presentation.ui.search.interfaces

import android.view.View
import com.anibalbastias.android.shopcart.domain.search.model.SearchRecentRealmData
import com.anibalbastias.android.shopcart.presentation.util.adapter.base.BaseBindClickHandler

/**
 * Created by anibalbastias on 2019-08-13.
 */

interface SearchRecentListener<T> : BaseBindClickHandler<T> {

    override fun onClickView(view: View, item: T)
    fun onClickSearchRecentItem(list: SearchRecentRealmData)
    fun onClearSearchItems()
}