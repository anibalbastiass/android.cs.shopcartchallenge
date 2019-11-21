package com.anibalbastias.android.shopcart.presentation.component

import com.anibalbastias.android.shopcart.presentation.module.ApplicationModule
import com.anibalbastias.android.shopcart.presentation.module.ShopCartAPIModule
import com.anibalbastias.android.shopcart.presentation.module.ViewModelModule
import com.anibalbastias.android.shopcart.presentation.MainActivity
import com.anibalbastias.android.shopcart.base.module.component.BaseApplicationComponent
import com.anibalbastias.android.shopcart.presentation.module.ShopCartRepositoryModule
import com.anibalbastias.android.shopcart.presentation.ui.entry.EntryFragment
import com.anibalbastias.android.shopcart.presentation.ui.resultitem.ResultItemFragment
import com.anibalbastias.android.shopcart.presentation.ui.search.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ShopCartRepositoryModule::class,
        ViewModelModule::class,
        ShopCartAPIModule::class]
)

interface ApplicationComponent : BaseApplicationComponent, FragmentInjector {
    fun inject(mainActivity: MainActivity)
}

interface FragmentInjector {
    fun inject(entryFragment: EntryFragment)
    fun inject(searchFragment: SearchFragment)
    fun inject(resultItemFragment: ResultItemFragment)
}