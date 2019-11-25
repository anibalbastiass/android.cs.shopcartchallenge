package com.anibalbastias.android.shopcart.domain.products.model

import com.anibalbastias.android.shopcart.domain.base.model.RealmString
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ProductsEntity : RealmObject() {

    @PrimaryKey
    @JvmField
    @SerializedName("keyword")
    var keyword: String? = null
    var results: RealmList<ProductsItemEntity?>? = null

    var productList: RealmList<RealmString>? = null

    init {
        productList = RealmList()
    }

    companion object {

        fun equals(
            userList1: List<ProductsEntity>?,
            userList2: List<ProductsEntity>?
        ): Boolean {
            var isEquals = true

            if (userList1 != null && userList2 != null && userList1.size == userList2.size) {
                for (i in userList1.indices) {
                    var user1 = userList1[i]
                    var user2 = userList2[i]

                    if (!equals(user1, user2)) {
                        isEquals = false
                        break
                    }
                }
            } else {
                isEquals = false
            }

            return isEquals
        }

        fun equals(user1: ProductsEntity, user2: ProductsEntity): Boolean {
            if (user1.keyword != user2.keyword) return false

            if (user1.results == null || user2.results == null || user1.results != user2.results)
                return false

            if (user1.productList != null && user2.productList != null &&
                user1.productList!!.size == user2.productList!!.size
            ) {
                for (i in 0 until user1.productList!!.size) {
                    val contact1 = user1.productList!![i]?.string
                    val contact2 = user2.productList!![i]?.string

                    if (contact1 != contact2) {
                        return false
                    }
                }
            }

            return true
        }
    }
}