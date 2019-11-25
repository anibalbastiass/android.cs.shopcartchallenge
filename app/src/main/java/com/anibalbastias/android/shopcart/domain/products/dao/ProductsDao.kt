package com.anibalbastias.android.shopcart.domain.products.dao

import com.anibalbastias.android.shopcart.domain.products.model.ProductsEntity
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults


/**
 * Created by anibalbastias on 2019-09-07.
 */

class ProductsDao(realm: Realm?) {

    private lateinit var mRealm: Realm

    init {
        realm?.let {
            mRealm = it
        }
    }

    fun save(user: ProductsEntity) {
        mRealm.executeTransaction { realm -> realm.copyToRealmOrUpdate(user) }
    }

    fun save(userList: List<ProductsEntity>) {
        mRealm.executeTransaction { realm -> realm.copyToRealmOrUpdate(userList) }
    }

    fun loadAll(): RealmResults<ProductsEntity> {
        return mRealm.where(ProductsEntity::class.java).findAll()
    }

    fun loadAllAsync(): RealmResults<ProductsEntity> {
        return mRealm.where(ProductsEntity::class.java).findAllAsync()
    }

    fun loadBy(id: Long): RealmObject {
        return mRealm.where(ProductsEntity::class.java).equalTo("id", id).findFirst()!!
    }

    fun loadByKeyword(keyword: String): ProductsEntity {
        return mRealm.where(ProductsEntity::class.java).equalTo("keyword", keyword).findFirst()!!
    }

    fun remove(item: RealmObject?) {
        mRealm.executeTransaction { item?.deleteFromRealm() }
    }

    fun removeAll() {
        mRealm.executeTransaction { mRealm.delete(ProductsEntity::class.java) }
    }

    fun count(): Long {
        return mRealm.where(ProductsEntity::class.java).count()
    }
}