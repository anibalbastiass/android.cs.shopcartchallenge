package com.anibalbastias.android.shopcart.domain.counters.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CounterEntity : RealmObject() {

    @PrimaryKey
    @JvmField
    var id: String? = null
    var title: String? = null
    var count: Int? = null
    var state: StateCounter? = StateCounter.DEFAULT
    var action: ActionCounter? = null

    enum class ActionCounter {
        CREATE,
        INC,
        DELETE
    }

    enum class StateCounter {
        DEFAULT,
        PENDENT,
        SENT,
        ERROR
    }

    companion object {

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