package com.anibalbastias.android.shopcart.domain.counters.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CounterEntity : RealmObject() {

    @PrimaryKey
    @JvmField
    var id: String? = null
    var title: String? = null
    var count: Int? = null
    var state: String? = STATE_COUNTER_DEFAULT
    var action: String? = null

    companion object {

        const val STATE_COUNTER_DEFAULT = "defaultState"
        const val STATE_COUNTER_PENDENT = "pendentState"
        const val STATE_COUNTER_SENT = "sentState"
        const val STATE_COUNTER_ERROR = "errorState"

        const val ACTION_COUNTER_CREATE = "createAction"
        const val ACTION_COUNTER_INC = "incAction"
        const val ACTION_COUNTER_DEC = "decAction"
        const val ACTION_COUNTER_DELETE = "deleteAction"

        fun equals(list1: List<CounterEntity>?, list2: List<CounterEntity>?): Boolean {

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

        fun equals(entity1: CounterEntity, entity2: CounterEntity): Boolean {

            if (entity1.id != entity2.id) return false

            if (entity1.title != entity2.title) return false

            if (entity1.count != entity2.count) return false

            if (entity1.id == null || entity2.id == null || entity1.id != entity2.id)
                return false

            if (entity1.title == null || entity2.title == null || entity1.title != entity2.title)
                return false

            if (entity1.count == null || entity2.count == null || entity1.count != entity2.count)
                return false

            return true
        }
    }
}