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
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.counters.CounterViewData
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.products.ProductsItemViewData
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.products.ProductsViewData
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.viewmodel.ShopCartViewModel
import com.anibalbastias.android.shopcart.presentation.util.*

class ShopCartFragment : BaseModuleFragment(), ShopCartItemListener<ProductsItemViewData> {

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
        initViewModel()
        fetchProducts()
    }

    private fun initViewModel() {
        implementObserver(shopCartViewModel.getProductsLiveData(),
            successBlock = { viewData -> getProductsData(viewData) },
            loadingBlock = { showLoadingView() },
            errorBlock = { showErrorView(it) })
    }

    private fun getProductsData(viewData: ProductsViewData) {
        shopCartViewModel.isLoading.set(false)
        shopCartViewModel.isError.set(false)
        binding.shopCartListSwipeRefreshLayout?.isRefreshing = false

        shopCartViewModel.shopCartList.set(
            viewData.products as? ArrayList<ProductsItemViewData?>
        )
    }

    private fun showErrorView(errorMessage: String?) {
        shopCartViewModel.isLoading.set(false)
    }

    private fun showLoadingView() {
        shopCartViewModel.isLoading.set(true)
    }

    private fun fetchProducts() {
        shopCartViewModel.apply {
            getProductsLiveData().value?.data?.let {
                getProductsData(it)
            } ?: run {
                isLoading.set(true)
                fetchAllProducts()
            }

            // Set Swipe Refresh Layout
            binding.shopCartListSwipeRefreshLayout?.initSwipe {
                fetchAllProducts()
            }
        }
    }

    private fun initToolbar() {
        binding.shopCartToolbar?.run {
            applyFontForToolbarTitle(activity!!)
            setNoArrowUpToolbar(activity!!)
        }
    }

    override fun onAddCounterItem(item: ProductsItemViewData) {
        shopCartViewModel.shopCartList.get()?.map {
            if (it?.itemId == item.itemId) {
                item.counter?.set(CounterViewData(count = 0))
            }
        }
    }

    override fun onIncCounterItem(item: ProductsItemViewData) {
        shopCartViewModel.shopCartList.get()?.map {
            if (it?.itemId == item.itemId) {
                //item.counter?.set(item.counter.get().count + 1)
            }
        }
    }

    override fun onDecCounterItem(item: ProductsItemViewData) {
        activity?.toast("Dec")
    }

    override fun onDeleteCounterItem(item: ProductsItemViewData) {
        activity?.toast("Delete")
    }
}