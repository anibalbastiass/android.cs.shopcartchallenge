package com.anibalbastias.android.shopcart.domain.products.model

import com.anibalbastias.android.shopcart.domain.counters.model.CounterEntity
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ProductsItemEntity : RealmObject() {

    @PrimaryKey
    @JvmField
    var itemId: String? = null
    var title: String? = null
    var subtitle: String? = null
    var amount: String? = null
    var imageUrl: String? = null
    var counter: CounterEntity? = null

    companion object {

        fun equals(list1: List<ProductsItemEntity>?, list2: List<ProductsItemEntity>?): Boolean {

            var isEquals = true

            if (list1 != null && list2 != null && list1.size == list2.size) {
                for (i in list1.indices) {
                    val user1 = list1[i]
                    val user2 = list2[i]

                    if (!equals(
                            user1,
                            user2
                        )
                    ) {
                        isEquals = false
                        break
                    }
                }
            } else {
                isEquals = false
            }

            return isEquals
        }

        fun equals(itemEntity1: ProductsItemEntity, itemEntity2: ProductsItemEntity): Boolean {

            if (itemEntity1.itemId != itemEntity2.itemId) return false

            if (itemEntity1.title != itemEntity2.title) return false

            if (itemEntity1.subtitle != itemEntity2.subtitle) return false

            if (itemEntity1.amount != itemEntity2.amount) return false

            if (itemEntity1.imageUrl != itemEntity2.imageUrl) return false

            if (itemEntity1.counter != itemEntity2.counter) return false

            if (itemEntity1.itemId == null || itemEntity2.itemId == null || itemEntity1.itemId != itemEntity2.itemId)
                return false

            if (itemEntity1.title == null || itemEntity2.title == null || itemEntity1.title != itemEntity2.title)
                return false

            if (itemEntity1.subtitle == null || itemEntity2.subtitle == null || itemEntity1.subtitle != itemEntity2.subtitle)
                return false

            if (itemEntity1.amount == null || itemEntity2.amount == null || itemEntity1.amount != itemEntity2.amount)
                return false

            if (itemEntity1.imageUrl == null || itemEntity2.imageUrl == null || itemEntity1.imageUrl != itemEntity2.imageUrl)
                return false

            if (itemEntity1.counter == null || itemEntity2.counter == null || itemEntity1.counter != itemEntity2.counter)
                return false

            return true
        }
    }
}