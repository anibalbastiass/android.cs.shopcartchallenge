package com.anibalbastias.android.shopcart.data.shopcart

/**
 * Created by Anibal Bastias Soto on 2019-11-21.
 */

object ShopCartApiConstants {
    private const val API_VERSION = "api/v1/"
    const val GET_PRODUCTS = "${API_VERSION}products"
    const val GET_COUNTERS = "${API_VERSION}counters"
    const val POST_COUNTER = "${API_VERSION}counter"
    const val DELETE_COUNTER = "${API_VERSION}counter"
    const val POST_COUNTER_INC = "${POST_COUNTER}/inc"
    const val POST_COUNTER_DEC = "${POST_COUNTER}/dec"
}