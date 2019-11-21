package com.anibalbastias.android.shopcart.presentation.ui.shopcart

import android.os.Bundle
import android.view.View
import androidx.lifecycle.SavedStateViewModelFactory
import com.anibalbastias.android.shopcart.R
import com.anibalbastias.android.shopcart.base.module.getViewModel
import com.anibalbastias.android.shopcart.base.view.BaseModuleFragment
import com.anibalbastias.android.shopcart.presentation.appComponent
import com.anibalbastias.android.shopcart.presentation.getAppContext

class ShopCartFragment : BaseModuleFragment() {

    override fun tagName(): String = this::class.java.simpleName
    override fun layoutId(): Int = R.layout.fragment_shop_cart_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent().inject(this)
        navBaseViewModel = getViewModel(viewModelFactory)
        sharedViewModel = activity!!.getViewModel(SavedStateViewModelFactory(getAppContext(), this))
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setNavController(this@ShopCartFragment.view)
    }
}