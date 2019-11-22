package com.anibalbastias.android.shopcart.presentation.module

import androidx.lifecycle.ViewModel
import com.anibalbastias.android.shopcart.base.module.ViewModelKey
import com.anibalbastias.android.shopcart.base.module.module.BaseViewModelModule
import com.anibalbastias.android.shopcart.base.view.NavBaseViewModel
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.viewmodel.ShopCartViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule : BaseViewModelModule() {

    @Binds
    @IntoMap
    @ViewModelKey(NavBaseViewModel::class)
    internal abstract fun navBaseViewModel(viewModel: NavBaseViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShopCartViewModel::class)
    internal abstract fun shopCartViewModel(viewModel: ShopCartViewModel): ViewModel

}