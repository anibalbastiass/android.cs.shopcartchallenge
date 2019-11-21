package com.anibalbastias.android.shopcart.base.module.module

import androidx.lifecycle.ViewModelProvider
import com.anibalbastias.android.shopcart.base.module.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class BaseViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}