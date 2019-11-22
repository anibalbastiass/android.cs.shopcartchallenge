package com.anibalbastias.android.shopcart.presentation.ui.shopcart

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.SavedStateViewModelFactory
import com.anibalbastias.android.shopcart.R
import com.anibalbastias.android.shopcart.base.module.getViewModel
import com.anibalbastias.android.shopcart.base.view.BaseModuleFragment
import com.anibalbastias.android.shopcart.databinding.FragmentShopCartListBinding
import com.anibalbastias.android.shopcart.presentation.appComponent
import com.anibalbastias.android.shopcart.presentation.getAppContext
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.interfaces.ShopCartItemListener
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.products.ProductsViewData
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.viewmodel.ShopCartViewModel
import com.anibalbastias.android.shopcart.presentation.util.applyFontForToolbarTitle
import com.anibalbastias.android.shopcart.presentation.util.setNoArrowUpToolbar
import com.anibalbastias.android.shopcart.presentation.util.toast

class ShopCartFragment : BaseModuleFragment(), ShopCartItemListener<ProductsViewData> {

    override fun tagName(): String = this::class.java.simpleName
    override fun layoutId(): Int = R.layout.fragment_shop_cart_list

    private lateinit var binding: FragmentShopCartListBinding
    private lateinit var shopCartViewModel: ShopCartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent().inject(this)
        navBaseViewModel = getViewModel(viewModelFactory)
        sharedViewModel = activity!!.getViewModel(SavedStateViewModelFactory(getAppContext(), this))
        shopCartViewModel = getViewModel(viewModelFactory)
        setHasOptionsMenu(true)

        // Test
        val list = arrayListOf<ProductsViewData?>()

        for (i in 1 until 10) {
            list.add(ProductsViewData())
        }

        shopCartViewModel.shopCartList.set(list)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setNavController(this@ShopCartFragment.view)

        binding = DataBindingUtil.bind<ViewDataBinding>(view) as FragmentShopCartListBinding
        //binding.sharedViewModel = sharedViewModel
        binding.shopCartViewModel = shopCartViewModel
        binding.shopCartItemListener = this
        binding.lifecycleOwner = this

        initToolbar()
    }

    private fun initToolbar() {
        binding.shopCartToolbar?.run {
            applyFontForToolbarTitle(activity!!)
            setNoArrowUpToolbar(activity!!)
        }
    }

    override fun onAddCounterItem(item: ProductsViewData) {
        activity?.toast("Add")
    }

    override fun onIncCounterItem(item: ProductsViewData) {
        activity?.toast("Inc")
    }

    override fun onDecCounterItem(item: ProductsViewData) {
        activity?.toast("Dec")
    }

    override fun onDeleteCounterItem(item: ProductsViewData) {
        activity?.toast("Delete")
    }
}