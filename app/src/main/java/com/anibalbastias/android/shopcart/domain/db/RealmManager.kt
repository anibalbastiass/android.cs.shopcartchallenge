package com.anibalbastias.android.shopcart.domain.db

import com.anibalbastias.android.shopcart.domain.counters.dao.CountersDao
import com.anibalbastias.android.shopcart.domain.products.dao.ProductsDao
import com.anibalbastias.android.shopcart.domain.products.model.ProductsItemEntity
import io.realm.Realm
import io.realm.kotlin.delete


/**
 * Created by anibalbastias on 2019-09-07.
 */

object RealmManager {

    private var mRealm: Realm? = null

    fun open(): Realm? {
        mRealm = Realm.getDefaultInstance()
        return mRealm
    }

    fun close() {
        if (mRealm != null) {
            mRealm!!.close()
        }
    }

    fun createProductListDao(): ProductsDao {
        checkForOpenRealm()
        return ProductsDao(mRealm)
    }

    fun createCounterListDao(): CountersDao {
        checkForOpenRealm()
        return CountersDao(mRealm)
    }

    fun clear() {
        checkForOpenRealm()
        mRealm?.executeTransaction { realm ->
            realm.delete<ProductsItemEntity>()
            //clear rest of your dao classes
        }
    }

    private fun checkForOpenRealm() {
        check(!(mRealm == null || mRealm!!.isClosed)) { "RealmManager: Realm is closed, call open() method first" }
    }
}