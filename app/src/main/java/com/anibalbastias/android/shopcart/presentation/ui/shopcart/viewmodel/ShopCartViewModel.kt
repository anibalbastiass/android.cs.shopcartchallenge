package com.anibalbastias.android.shopcart.presentation.ui.shopcart.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.anibalbastias.android.shopcart.R
import com.anibalbastias.android.shopcart.base.view.BaseViewModel
import com.anibalbastias.android.shopcart.base.view.Resource
import com.anibalbastias.android.shopcart.data.dataStoreFactory.products.model.ProductsData
import com.anibalbastias.android.shopcart.domain.counters.usecase.*
import com.anibalbastias.android.shopcart.domain.products.usecase.GetProductsUseCase
import javax.inject.Inject

class ShopCartViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCountersUseCase: GetCountersUseCase,
    private val postCountersUseCase: PostCountersUseCase,
    private val postIncCountersUseCase: PostIncCountersUseCase,
    private val postDecCountersUseCase: PostDecCountersUseCase,
    private val deleteCountersUseCase: DeleteCountersUseCase
    //private val searchViewDataMapper: SearchViewDataMapper
) : BaseViewModel() {

    // region Observables
    var isLoading: ObservableBoolean = ObservableBoolean(false)
    var isError: ObservableBoolean = ObservableBoolean(false)
    // endregion


    var shopCartItemLayout: Int = R.layout.view_cell_shop_cart_item
    var shopCartList: ObservableField<ArrayList<ProductsData?>> =
        ObservableField(
            arrayListOf()
        )

    private val getProductsLiveData: MutableLiveData<Resource<ProductsData>> =
        MutableLiveData()
}