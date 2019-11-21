package com.anibalbastias.android.shopcart.base.module

import com.anibalbastias.android.shopcart.presentation.ShopCartApplication
import com.anibalbastias.android.shopcart.domain.base.executor.APIPostExecutionThread
import com.anibalbastias.android.shopcart.domain.base.executor.APIThreadExecutor
import com.anibalbastias.android.shopcart.base.module.executor.JobExecutor
import com.anibalbastias.android.shopcart.base.module.executor.UIThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class BaseApplicationModule(private val application: ShopCartApplication) {

    @Provides
    @Singleton
    fun provideApp(): ShopCartApplication = application

    @Provides
    @Singleton
    fun provideAPIThreadExecutor(jobExecutor: JobExecutor): APIThreadExecutor = jobExecutor

    @Provides
    @Singleton
    fun provideAPIPostExecutionThread(uiThread: UIThread): APIPostExecutionThread = uiThread
}