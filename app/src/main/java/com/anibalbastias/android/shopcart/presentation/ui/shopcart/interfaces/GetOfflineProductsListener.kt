package com.anibalbastias.android.shopcart.presentation.ui.shopcart.interfaces

import com.anibalbastias.android.shopcart.domain.products.model.ProductsEntity
import io.realm.RealmResults

/**
 * Created by anibalbastias on 2019-08-13.
 */

interface GetOfflineProductsListener {
    fun onGetProductsFromRealm(list: RealmResults<ProductsEntity>?)
}