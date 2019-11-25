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
import com.anibalbastias.android.shopcart.domain.products.model.ProductsEntity
import com.anibalbastias.android.shopcart.presentation.appComponent
import com.anibalbastias.android.shopcart.presentation.getAppContext
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.interfaces.GetOfflineProductsListener
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.interfaces.ShopCartItemListener
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.counters.CounterActionViewData
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.counters.CounterViewData
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.products.ProductsItemViewData
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.products.ProductsViewData
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.viewmodel.ShopCartViewModel
import com.anibalbastias.android.shopcart.presentation.util.applyFontForToolbarTitle
import com.anibalbastias.android.shopcart.presentation.util.implementObserver
import com.anibalbastias.android.shopcart.presentation.util.initSwipe
import com.anibalbastias.android.shopcart.presentation.util.setNoArrowUpToolbar
import io.realm.RealmResults

class ShopCartFragment : BaseModuleFragment(),
    ShopCartItemListener<ProductsItemViewData>,
    GetOfflineProductsListener {

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
        binding.shopCartViewModel = shopCartViewModel
        binding.shopCartItemListener = this
        binding.lifecycleOwner = this

        initToolbar()
        initViewModel()
        fetchProducts()
    }

    private fun initViewModel() {
        // Fetch Products
        implementObserver(shopCartViewModel.getProductsLiveData(),
            successBlock = { viewData -> getProductsData(viewData) },
            loadingBlock = { showLoadingView() },
            errorBlock = { showErrorView(it) })

        // Fetch Counters
        implementObserver(shopCartViewModel.getCountersLiveData(),
            successBlock = { viewData -> getCountersData(viewData = viewData) },
            loadingBlock = { showLoadingView() },
            errorBlock = { showErrorView(it) })

        // Post Create counter
        implementObserver(shopCartViewModel.getPostCreateCounterLiveData(),
            successBlock = { viewData ->
                getCountersData(
                    CounterActionViewData.CREATE,
                    viewData
                )
            },
            loadingBlock = { showLoadingView() },
            errorBlock = { showErrorView(it, CounterActionViewData.CREATE) })

        // Post Inc counter by id
        implementObserver(shopCartViewModel.getPostIncCountersLiveData(),
            successBlock = { viewData ->
                getCountersData(
                    CounterActionViewData.INC,
                    viewData
                )
            },
            loadingBlock = { showLoadingView() },
            errorBlock = { showErrorView(it, CounterActionViewData.INC) })

        // Post Dec counter by id
        implementObserver(shopCartViewModel.getPostDecCountersLiveData(),
            successBlock = { viewData ->
                getCountersData(
                    CounterActionViewData.DEC,
                    viewData
                )
            },
            loadingBlock = { showLoadingView() },
            errorBlock = { showErrorView(it, CounterActionViewData.DEC) })

        // Delete counter by id
        implementObserver(shopCartViewModel.getDeleteCounterLiveData(),
            successBlock = { viewData ->
                getCountersData(
                    CounterActionViewData.DELETE,
                    viewData
                )
            },
            loadingBlock = { showLoadingView() },
            errorBlock = { showErrorView(it, CounterActionViewData.DELETE) })
    }

    private fun getCountersData(
        counterActionView: CounterActionViewData? = null,
        viewData: List<CounterViewData?>
    ) {
        shopCartViewModel.setAndMapCounters(counterActionView, viewData)
    }

    private fun getProductsData(viewData: ProductsViewData) {
        shopCartViewModel.apply {
            isLoading.set(false)
            isError.set(false)
            binding.shopCartListSwipeRefreshLayout?.isRefreshing = false

            shopCartList.set(
                viewData.products as? ArrayList<ProductsItemViewData?>
            )

            // Get Counters for Shop Cart
            fetchAllCounters()
        }
    }

    private fun showErrorView(
        errorMessage: String?,
        counterActionView: CounterActionViewData? = null
    ) {
        shopCartViewModel.isLoading.set(false)
        binding.shopCartListSwipeRefreshLayout?.isRefreshing = false

        when (counterActionView) {
            CounterActionViewData.CREATE -> {

            }
            CounterActionViewData.INC -> {

            }
            CounterActionViewData.DEC -> {

            }
            CounterActionViewData.DELETE -> {

            }
            else -> {
                shopCartViewModel.loadProductsListAsync(this)
            }
        }
    }

    private fun showLoadingView() {
        //shopCartViewModel.isLoading.set(true)
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
                fetchAllProducts(showLoading = false)
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
        shopCartViewModel.addCounterItem(item)
    }

    override fun onIncCounterItem(item: ProductsItemViewData) {
        shopCartViewModel.onIncCounterItem(item)
    }

    override fun onDecCounterItem(item: ProductsItemViewData) {
        if (item.counter?.get()?.count!! > 1)
            shopCartViewModel.onDecCounterItem(item)
        else
            shopCartViewModel.onDeleteCounterItem(item)
    }

    override fun onGetProductsFromRealm(list: RealmResults<ProductsEntity>?) {
        shopCartViewModel.setOfflineProducts(list)
    }
}