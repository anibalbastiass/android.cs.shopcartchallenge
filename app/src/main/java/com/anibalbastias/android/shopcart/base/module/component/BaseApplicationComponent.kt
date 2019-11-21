package com.anibalbastias.android.shopcart.base.module.component

import com.anibalbastias.android.shopcart.presentation.ShopCartApplication
import com.anibalbastias.android.shopcart.base.module.executor.JobExecutor
import com.anibalbastias.android.shopcart.base.module.executor.UIThread


interface BaseApplicationComponent {

    fun inject(application: ShopCartApplication)
    fun threadExecutor(): JobExecutor
    fun postExecutionThread(): UIThread
}