package com.anibalbastias.android.shopcart.presentation.ui.search.interfaces

import com.anibalbastias.android.shopcart.domain.search.model.SearchRecentRealmData
import io.realm.RealmResults

/**
 * Created by anibalbastias on 2019-08-13.
 */

interface GetSearchRecentsListener {
    fun onGetRecentSearchFromRealm(list: RealmResults<SearchRecentRealmData>?)
}