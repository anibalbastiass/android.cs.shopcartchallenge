package com.anibalbastias.android.shopcart.presentation.ui.search.mapper

import com.anibalbastias.android.shopcart.data.dataStoreFactory.search.model.SearchMusicData
import com.anibalbastias.android.shopcart.domain.base.Mapper
import com.anibalbastias.android.shopcart.presentation.ui.search.model.SearchMusicViewData
import com.anibalbastias.android.shopcart.presentation.ui.search.model.WrapperViewData
import javax.inject.Inject

/**
 * Created by anibalbastias on 2019-09-07.
 */

open class SearchViewDataMapper @Inject constructor(
    private val wrapperViewDataMapper: WrapperViewDataMapper
) : Mapper<SearchMusicViewData?, SearchMusicData?> {

    override fun executeMapping(type: SearchMusicData?): SearchMusicViewData? {
        return type?.let {  item ->
            SearchMusicViewData(
                resultCount = item.resultCount,
                results = item.results?.map { wrapperViewDataMapper.executeMapping(it) } as ArrayList<WrapperViewData?>?
            )
        }
    }
}