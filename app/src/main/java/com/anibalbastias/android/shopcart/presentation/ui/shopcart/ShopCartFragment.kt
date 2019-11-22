package com.anibalbastias.android.shopcart.presentation.ui.shopcart

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.SavedStateViewModelFactory
import com.anibalbastias.android.shopcart.R
import com.anibalbastias.android.shopcart.base.module.getViewModel
import com.anibalbastias.android.shopcart.base.view.BaseModuleFragment
import com.anibalbastias.android.shopcart.data.dataStoreFactory.counters.model.CounterData
import com.anibalbastias.android.shopcart.data.dataStoreFactory.products.model.ProductsData
import com.anibalbastias.android.shopcart.databinding.FragmentShopCartListBinding
import com.anibalbastias.android.shopcart.presentation.appComponent
import com.anibalbastias.android.shopcart.presentation.getAppContext
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.interfaces.ShopCartItemListener
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.viewmodel.ShopCartViewModel
import com.anibalbastias.android.shopcart.presentation.util.applyFontForToolbarTitle
import com.anibalbastias.android.shopcart.presentation.util.setArrowUpToolbar
import com.anibalbastias.android.shopcart.presentation.util.setNoArrowUpToolbar

class ShopCartFragment : BaseModuleFragment(), ShopCartItemListener<CounterData> {

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
        val list = arrayListOf<ProductsData?>()
        list.add(ProductsData())
        list.add(ProductsData())
        list.add(ProductsData())
        list.add(ProductsData())
        list.add(ProductsData())
        list.add(ProductsData())
        list.add(ProductsData())
        list.add(ProductsData())
        list.add(ProductsData())
        list.add(ProductsData())
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

    override fun onClickView(view: View, item: CounterData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAddCounterItem(item: CounterData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onIncCounterItem(item: CounterData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDecCounterItem(item: CounterData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDeleteCounterItem(item: CounterData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}