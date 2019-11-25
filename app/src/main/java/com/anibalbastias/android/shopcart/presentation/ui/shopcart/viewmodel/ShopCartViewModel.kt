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
import com.anibalbastias.android.shopcart.data.dataStoreFactory.counters.model.CounterData
import com.anibalbastias.android.shopcart.domain.counters.model.CounterEntity
import com.anibalbastias.android.shopcart.domain.counters.usecase.*
import com.anibalbastias.android.shopcart.domain.db.RealmManager
import com.anibalbastias.android.shopcart.domain.products.model.ProductsItemEntity
import com.anibalbastias.android.shopcart.domain.products.model.ProductsEntity
import com.anibalbastias.android.shopcart.domain.products.usecase.GetProductsUseCase
import com.anibalbastias.android.shopcart.presentation.context
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.interfaces.GetOfflineProductsListener
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.mapper.counters.CounterListViewDataMapper
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.mapper.products.ProductsViewDataMapper
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.mapper.realm.ProductsRealmMapper
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.counters.CounterActionViewData
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.counters.CounterViewData
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.products.ProductsItemViewData
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.products.ProductsViewData
import com.anibalbastias.android.shopcart.presentation.util.isConnectingToInternet
import io.realm.RealmList
import io.realm.RealmResults
import javax.inject.Inject

class ShopCartViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCountersUseCase: GetCountersUseCase,
    private val postCountersUseCase: PostCountersUseCase,
    private val postIncCountersUseCase: PostIncCountersUseCase,
    private val postDecCountersUseCase: PostDecCountersUseCase,
    private val deleteCountersUseCase: DeleteCountersUseCase,
    private val productsViewDataMapper: ProductsViewDataMapper,
    private val counterListViewDataMapper: CounterListViewDataMapper,
    private val productsRealmMapper: ProductsRealmMapper
) : BaseViewModel() {

    // region Observables
    var isLoading: ObservableBoolean = ObservableBoolean(false)
    var isError: ObservableBoolean = ObservableBoolean(false)
    var shopCartList: ObservableField<ArrayList<ProductsItemViewData?>> =
        ObservableField(arrayListOf())
    var shopCartTotalCount: ObservableInt = ObservableInt(0)
    var requestItem: ObservableField<ProductsItemViewData> = ObservableField(ProductsItemViewData())
    // endregion

    var shopCartItemLayout: Int? = R.layout.view_cell_shop_cart_item

    override fun onCleared() {
        getProductsUseCase.dispose()
        getCountersUseCase.dispose()
        postCountersUseCase.dispose()
        postIncCountersUseCase.dispose()
        postDecCountersUseCase.dispose()
        deleteCountersUseCase.dispose()
        super.onCleared()
    }

    //region Live Data
    private val getProductsLiveData: MutableLiveData<Resource<ProductsViewData>> = MutableLiveData()

    fun getProductsLiveData() = getProductsLiveData

    private val getCountersLiveData: MutableLiveData<Resource<List<CounterViewData?>>> =
        MutableLiveData()

    fun getCountersLiveData() = getCountersLiveData

    private val postCreateCounterLiveData: MutableLiveData<Resource<List<CounterViewData?>>> =
        MutableLiveData()

    fun getPostCreateCounterLiveData() = postCreateCounterLiveData

    private val postIncCounterLiveData: MutableLiveData<Resource<List<CounterViewData?>>> =
        MutableLiveData()

    fun getPostIncCountersLiveData() = postIncCounterLiveData

    private val postDecCounterLiveData: MutableLiveData<Resource<List<CounterViewData?>>> =
        MutableLiveData()

    fun getPostDecCountersLiveData() = postDecCounterLiveData

    private val deleteCounterLiveData: MutableLiveData<Resource<List<CounterViewData?>>> =
        MutableLiveData()

    fun getDeleteCounterLiveData() = deleteCounterLiveData
    //endregion

    //region Counters local methods
    private fun updateShopCartTotalCount() {
        var total = 0
        shopCartList.get()?.map {
            it?.counter?.get()?.count?.let { count ->
                total += count
            }
        }
        shopCartTotalCount.set(total)
    }

    private fun ProductsItemViewData.mapCounterProduct(counterBlock: (ProductsItemViewData?) -> Unit) {
        shopCartList.get()?.map {
            if (it?.itemId == itemId) {
                it?.isUpdating = true
                counterBlock.invoke(it)
            }
        }
        updateShopCartTotalCount()
    }

    private fun updateTempCounter(item: ProductsItemViewData, newValue: Int? = null) {
        val tempCounter = item.counter?.get()!!
        item.counter?.set(
            CounterViewData(
                id = tempCounter.id,
                title = tempCounter.title,
                count = newValue?.let { tempCounter.count!! + it } ?: 0
            )
        )
    }

    fun addCounterItem(item: ProductsItemViewData) {
        item.mapCounterProduct {
            it?.counter?.set(CounterViewData(count = 1))

            processCounter(
                counterActionView = CounterActionViewData.CREATE,
                request = CounterData(title = it?.itemId),
                liveData = postCreateCounterLiveData
            )
        }
    }

    fun onIncCounterItem(item: ProductsItemViewData) {
        item.mapCounterProduct {
            item.counter?.get()?.id?.let { id ->
                updateTempCounter(item, 1)

                processCounter(
                    counterActionView = CounterActionViewData.INC,
                    request = CounterData(id = id),
                    liveData = postIncCounterLiveData
                )
            }
        }
    }

    fun onDecCounterItem(item: ProductsItemViewData) {
        item.mapCounterProduct {
            item.counter?.get()?.id?.let { id ->
                updateTempCounter(item, -1)

                processCounter(
                    counterActionView = CounterActionViewData.DEC,
                    request = CounterData(id = id),
                    liveData = postDecCounterLiveData
                )
            }
        }
    }

    fun onDeleteCounterItem(item: ProductsItemViewData) {
        item.mapCounterProduct {
            item.counter?.get()?.id?.let { id ->
                updateTempCounter(item)

                processCounter(
                    counterActionView = CounterActionViewData.DELETE,
                    request = CounterData(id = id),
                    liveData = deleteCounterLiveData
                )
            }
        }
    }

    fun checkDecDeleteCounterItem(item: ProductsItemViewData) {
        if (item.counter?.get()?.count!! > 1)
            onDecCounterItem(item)
        else
            onDeleteCounterItem(item)
    }

    fun setAndMapCounters(
        counterActionView: CounterActionViewData? = null,
        counterList: List<CounterViewData?>
    ) {
        val productList = shopCartList.get()

        // Map between products and counters for match
        productList?.map { productItem ->
            counterList.map { counterItem ->
                if (productItem?.itemId == counterItem?.title) {
                    // Match product with counter
                    productItem?.counter?.set(counterItem)
                    productItem?.isUpdating = false

                    // If create a counter, then increment counter to 1
                    when (counterActionView) {
                        CounterActionViewData.CREATE -> {
                            // Create item and then Increment value
                            if (counterItem?.count == 0) {
                                // Fix initial value when create
                                if (counterItem.count == 0 && !isError.get()) {
                                    counterItem.count = 1

                                    processCounter(
                                        counterActionView = CounterActionViewData.INC,
                                        request = CounterData(id = counterItem.id),
                                        liveData = postIncCounterLiveData
                                    )
                                }
                            }
                        }
                        else -> updateShopCartTotalCount()
                    }
                }
            }
        }

        // Save products and counters in local database
        val products = ProductsEntity()
        products.keyword = "products"

        val realmList = RealmList<ProductsItemEntity?>()
        productList?.map { item ->
            realmList.add(productsRealmMapper.executeMapping(item))
        }
        products.results = realmList

        RealmManager.createProductListDao().save(products)
    }
    //endregion

    //region API Calls
    fun fetchAllCounters() {
        getCountersLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getCountersUseCase.execute(
            BaseSubscriber(
                context?.applicationContext, this, counterListViewDataMapper,
                getCountersLiveData, isLoading, isError
            )
        )
    }

    private fun processCounter(
        liveData: MutableLiveData<Resource<List<CounterViewData?>>>,
        request: CounterData? = null,
        counterActionView: CounterActionViewData
    ) {
        if (context?.isConnectingToInternet() == true) {
            liveData.postValue(Resource(ResourceState.LOADING, null, null))

            val useCase = when (counterActionView) {
                CounterActionViewData.CREATE -> {
                    deletePendentCounterAsync(CounterEntity.ACTION_COUNTER_CREATE)
                    postCountersUseCase
                }
                CounterActionViewData.INC -> {
                    deletePendentCounterAsync(CounterEntity.ACTION_COUNTER_INC)
                    postIncCountersUseCase
                }
                CounterActionViewData.DEC -> {
                    deletePendentCounterAsync(CounterEntity.ACTION_COUNTER_DEC)
                    postDecCountersUseCase
                }
                CounterActionViewData.DELETE -> {
                    deletePendentCounterAsync(CounterEntity.ACTION_COUNTER_DELETE)
                    deleteCountersUseCase
                }
                CounterActionViewData.DEFAULT -> TODO()
            }

            return useCase.execute(
                BaseSubscriber(
                    context?.applicationContext, this, counterListViewDataMapper,
                    liveData, isLoading, isError
                ), request
            )
        } else {
            // Create pendent counters into local database
            when (counterActionView) {
                CounterActionViewData.CREATE -> {
                    processPendentCounterAsync(CounterEntity.ACTION_COUNTER_CREATE)
                }
                CounterActionViewData.INC -> {
                    processPendentCounterAsync(CounterEntity.ACTION_COUNTER_INC)
                }
                CounterActionViewData.DEC -> {
                    processPendentCounterAsync(CounterEntity.ACTION_COUNTER_DEC)
                }
                CounterActionViewData.DELETE -> {
                    processPendentCounterAsync(CounterEntity.ACTION_COUNTER_DELETE)
                }
                CounterActionViewData.DEFAULT -> TODO()
            }
        }
    }

    fun fetchAllProducts(showLoading: Boolean? = true) {
        isLoading.set(showLoading == true)
        getProductsLiveData.postValue(Resource(ResourceState.LOADING, null, null))

        return getProductsUseCase.execute(
            BaseSubscriber(
                context?.applicationContext, this, productsViewDataMapper,
                getProductsLiveData, isLoading, isError
            )
        )
    }
    //endregion

    //region Realm Methods
    fun loadProductsListAsync(callback: GetOfflineProductsListener?) {
        val dataList = RealmManager.createProductListDao().loadAllAsync()
        dataList.addChangeListener { t, _ ->
            callback?.onGetProductsFromRealm(t)
        }
    }

    private fun getCurrentCounter(action: String): CounterEntity {
        val reqItem = requestItem.get()?.counter?.get()!!
        val counter = CounterEntity()
        counter.id = reqItem.id
        counter.title = reqItem.title
        counter.count = reqItem.count
        counter.state = CounterEntity.STATE_COUNTER_PENDENT
        counter.action = action
        return counter
    }

    private fun processPendentCounterAsync(action: String) {
        RealmManager.createCounterListDao().save(getCurrentCounter(action))
    }

    private fun deletePendentCounterAsync(action: String) {
        if (getCurrentCounter(action).state == CounterEntity.STATE_COUNTER_PENDENT) {
            RealmManager.createCounterListDao().remove(getCurrentCounter(action))
        }
    }

    fun setOfflineProducts(list: RealmResults<ProductsEntity>?) {
        val productList = arrayListOf<ProductsItemViewData?>()

        if (list?.isNotEmpty() == true) {
            list[0]?.let { item ->
                item.results?.map {
                    productList.add(productsRealmMapper.inverseExecuteMapping(it))
                }
            }
            shopCartList.set(productList)
        }
    }

    //endregion
}