package com.anibalbastias.android.shopcart.presentation.ui.shopcart.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.anibalbastias.android.shopcart.R
import com.anibalbastias.android.shopcart.base.subscriber.BaseSubscriber
import com.anibalbastias.android.shopcart.base.view.BaseViewModel
import com.anibalbastias.android.shopcart.base.view.Resource
import com.anibalbastias.android.shopcart.base.view.ResourceState
import com.anibalbastias.android.shopcart.domain.counters.usecase.*
import com.anibalbastias.android.shopcart.domain.products.usecase.GetProductsUseCase
import com.anibalbastias.android.shopcart.presentation.context
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.mapper.counters.CounterViewDataMapper
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.mapper.products.ProductsViewDataMapper
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.counters.CounterViewData
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.products.ProductsItemViewData
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.products.ProductsViewData
import javax.inject.Inject

class ShopCartViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCountersUseCase: GetCountersUseCase,
    private val postCountersUseCase: PostCountersUseCase,
    private val postIncCountersUseCase: PostIncCountersUseCase,
    private val postDecCountersUseCase: PostDecCountersUseCase,
    private val deleteCountersUseCase: DeleteCountersUseCase,
    private val productsViewDataMapper: ProductsViewDataMapper,
    private val counterViewDataMapper: CounterViewDataMapper
) : BaseViewModel() {

    // region Observables
    var isLoading: ObservableBoolean = ObservableBoolean(false)
    var isError: ObservableBoolean = ObservableBoolean(false)
    // endregion

    override fun onCleared() {
        getProductsUseCase.dispose()
        getCountersUseCase.dispose()
        postCountersUseCase.dispose()
        postIncCountersUseCase.dispose()
        postDecCountersUseCase.dispose()
        deleteCountersUseCase.dispose()
        super.onCleared()
    }

    var shopCartItemLayout: Int = R.layout.view_cell_shop_cart_item
    var shopCartList: ObservableField<ArrayList<ProductsItemViewData?>> =
        ObservableField(
            arrayListOf()
        )

    var shopCartTotalCount: ObservableInt = ObservableInt(0)

    private fun updateShopCartTotalCount() {
        var total = 0
        shopCartList.get()?.map {
            total += it?.counter?.get()?.count!!
        }
        shopCartTotalCount.set(total)
    }

    private val getProductsLiveData: MutableLiveData<Resource<ProductsViewData>> =
        MutableLiveData()

    fun getProductsLiveData() = getProductsLiveData

    fun ProductsItemViewData.mapCounterProduct(counterBlock: (ProductsItemViewData?) -> Unit) {
        shopCartList.get()?.map {
            if (it?.itemId == itemId) {
                counterBlock.invoke(it)
            }
        }
        updateShopCartTotalCount()
    }

    fun addCounterItem(item: ProductsItemViewData) {
        item.mapCounterProduct {
            it?.counter?.set(CounterViewData(count = 1))
        }
    }

    fun onIncCounterItem(item: ProductsItemViewData) {
        item.mapCounterProduct {
            item.counter?.set(CounterViewData(count = item.counter?.get()?.count!! + 1))
        }
    }

    fun onDecCounterItem(item: ProductsItemViewData) {
        item.mapCounterProduct {
            item.counter?.set(CounterViewData(count = item.counter?.get()?.count!! - 1))
        }
    }

    fun onDeleteCounterItem(item: ProductsItemViewData) {
        item.mapCounterProduct {
            it?.counter?.set(CounterViewData(count = 0))
        }
    }

    fun fetchAllProducts() {

        isLoading.set(true)
        getProductsLiveData.postValue(Resource(ResourceState.LOADING, null, null))

        return getProductsUseCase.execute(
            BaseSubscriber(
                context?.applicationContext, this, productsViewDataMapper,
                getProductsLiveData, isLoading, isError
            )
        )
    }
}