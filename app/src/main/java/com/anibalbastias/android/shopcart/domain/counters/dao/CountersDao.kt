package com.anibalbastias.android.shopcart.domain.counters.dao

import com.anibalbastias.android.shopcart.domain.counters.model.CounterEntity
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import java.lang.IllegalArgumentException


/**
 * Created by anibalbastias on 2019-09-07.
 */

class CountersDao(realm: Realm?) {

    private lateinit var mRealm: Realm

    init {
        realm?.let {
            mRealm = it
        }
    }

    fun save(user: CounterEntity) {
        mRealm.executeTransaction { realm -> realm.copyToRealmOrUpdate(user) }
    }

    fun save(userList: List<CounterEntity>) {
        mRealm.executeTransaction { realm -> realm.copyToRealmOrUpdate(userList) }
    }

    fun loadAll(): RealmResults<CounterEntity> {
        return mRealm.where(CounterEntity::class.java).findAll()
    }

    fun loadAllAsync(): RealmResults<CounterEntity> {
        return mRealm.where(CounterEntity::class.java).findAllAsync()
    }

    fun loadBy(id: Long): RealmObject {
        return mRealm.where(CounterEntity::class.java).equalTo("id", id).findFirst()!!
    }

    fun loadByTitle(title: String): CounterEntity {
        return mRealm.where(CounterEntity::class.java).equalTo("title", title).findFirst()!!
    }

    fun remove(item: CounterEntity?) {
        mRealm.executeTransaction {
            try {
                item?.deleteFromRealm()
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            }
        }
    }

    fun removeAll() {
        mRealm.executeTransaction { mRealm.delete(CounterEntity::class.java) }
    }

    fun count(): Long {
        return mRealm.where(CounterEntity::class.java).count()
    }
}