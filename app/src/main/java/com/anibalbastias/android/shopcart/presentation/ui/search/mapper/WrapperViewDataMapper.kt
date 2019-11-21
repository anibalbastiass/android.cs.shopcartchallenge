package com.anibalbastias.android.shopcart.presentation.ui.search.mapper

import com.anibalbastias.android.shopcart.data.dataStoreFactory.common.WrapperData
import com.anibalbastias.android.shopcart.data.dataStoreFactory.search.model.CollectionResultItemData
import com.anibalbastias.android.shopcart.data.dataStoreFactory.search.model.TrackResultItemData
import com.anibalbastias.android.shopcart.data.shopcart.ShopCartAPIGSONManager
import com.anibalbastias.android.shopcart.domain.base.Mapper
import com.anibalbastias.android.shopcart.presentation.ui.search.model.WrapperViewData
import javax.inject.Inject

/**
 * Created by anibalbastias on 2019-09-08.
 */

class WrapperViewDataMapper @Inject constructor(
    private val trackResultItemViewDataMapper: TrackResultItemViewDataMapper,
    private val collectionResultItemViewDataMapper: CollectionResultItemViewDataMapper
) : Mapper<WrapperViewData?, WrapperData?> {

    override fun executeMapping(type: WrapperData?): WrapperViewData? {
        return type?.let {
            when (type.wrapperType) {
                ShopCartAPIGSONManager.TRACK_WRAPPER_TYPE -> {
                    trackResultItemViewDataMapper.executeMapping(type as TrackResultItemData)
                }
                ShopCartAPIGSONManager.COLLECTION_WRAPPER_TYPE -> {
                    collectionResultItemViewDataMapper.executeMapping(type as CollectionResultItemData)
                }
                else -> null
            }
        }
    }
}