package com.anibalbastias.android.shopcart.data.shopcart

import com.anibalbastias.android.shopcart.data.dataStoreFactory.common.WrapperData
import com.anibalbastias.android.shopcart.data.shopcart.deserializer.ShopCartWrapperDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class ShopCartAPIGSONManager private constructor() {

    @Retention(AnnotationRetention.SOURCE)
    annotation class SectionType

    @Retention(AnnotationRetention.SOURCE)
    annotation class ActionType

    private fun createGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    private fun createGsonBuilder(): GsonBuilder {
        val gsonBuilder = GsonBuilder()

        gsonBuilder.registerTypeAdapter(WrapperData::class.java, ShopCartWrapperDeserializer())

        return gsonBuilder
    }

    companion object {
        private var instance: ShopCartAPIGSONManager? = null

        //region Wrapper
        const val COLLECTION_WRAPPER_TYPE = "collection"
        const val TRACK_WRAPPER_TYPE = "track"
        //endregion

        fun create(): Gson {
            if (instance == null) {
                instance = ShopCartAPIGSONManager()
            }
            return instance!!.createGson()
        }

        fun createGsonBuilder(): GsonBuilder {
            if (instance == null) {
                instance = ShopCartAPIGSONManager()
            }
            return instance!!.createGsonBuilder()
        }
    }
}