package com.anibalbastias.android.shopcart.data.shopcart.deserializer

import com.anibalbastias.android.shopcart.data.dataStoreFactory.common.WrapperData
import com.anibalbastias.android.shopcart.data.dataStoreFactory.search.model.CollectionResultItemData
import com.anibalbastias.android.shopcart.data.dataStoreFactory.search.model.TrackResultItemData
import com.anibalbastias.android.shopcart.data.shopcart.ShopCartAPIGSONManager
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ShopCartWrapperDeserializer : JsonDeserializer<WrapperData> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): WrapperData? {

        var ret: WrapperData? = null
        val type = json?.asJsonObject?.get("wrapperType")?.asString

        when (type) {
            ShopCartAPIGSONManager.TRACK_WRAPPER_TYPE -> {
                ret = context?.deserialize<TrackResultItemData>(json,
                    TrackResultItemData::class.java)
            }
            ShopCartAPIGSONManager.COLLECTION_WRAPPER_TYPE -> {
                ret = context?.deserialize<CollectionResultItemData>(
                    json,
                    CollectionResultItemData::class.java
                )
            }
            else -> {

            }
            //endregion
        }

        return ret
    }

}