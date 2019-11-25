package com.anibalbastias.android.shopcart.presentation.ui.shopcart.viewmodel

import com.anibalbastias.android.shopcart.base.subscriber.BaseSubscriber
import com.anibalbastias.android.shopcart.base.view.ResourceState
import com.anibalbastias.android.shopcart.data.dataStoreFactory.counters.model.CounterData
import com.anibalbastias.android.shopcart.data.dataStoreFactory.products.model.ProductsData
import com.anibalbastias.android.shopcart.domain.counters.usecase.*
import com.anibalbastias.android.shopcart.domain.products.usecase.GetProductsUseCase
import com.anibalbastias.android.shopcart.presentation.BaseDataManager.Companion.getErrorMessage
import com.anibalbastias.android.shopcart.presentation.BaseUnitTest
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.mapper.counters.CounterListViewDataMapper
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.mapper.products.ProductsViewDataMapper
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.mapper.realm.ProductsRealmMapper
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.counters.CounterActionViewData
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.counters.CounterViewData
import com.anibalbastias.android.shopcart.presentation.ui.shopcart.model.products.ProductsViewData
import com.nhaarman.mockito_kotlin.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito

class ShopCartViewModelTest : BaseUnitTest() {

    override fun showErrorMessage(): Boolean = false

    override fun initCaptors() {
        captorGetProducts = argumentCaptor()
        captorGetCounters = argumentCaptor()
    }

    override fun initDataMocks() {
        getProductsUseCase = mock()
        getCountersUseCase = mock()
        postCountersUseCase = mock()
        postIncCountersUseCase = mock()
        postDecCountersUseCase = mock()
        deleteCountersUseCase = mock()
        productsViewDataMapper = mock()
        counterListViewDataMapper = mock()
        productsRealmMapper = mock()
    }

    override fun initViewModel() {
        shopCartViewModel = ShopCartViewModel(
            getProductsUseCase,
            getCountersUseCase,
            postCountersUseCase,
            postIncCountersUseCase,
            postDecCountersUseCase,
            deleteCountersUseCase,
            productsViewDataMapper,
            counterListViewDataMapper,
            productsRealmMapper
        )
    }

    // region Mocked vars
    @Mock
    lateinit var getProductsUseCase: GetProductsUseCase
    @Mock
    lateinit var getCountersUseCase: GetCountersUseCase
    @Mock
    lateinit var postCountersUseCase: PostCountersUseCase
    @Mock
    lateinit var postIncCountersUseCase: PostIncCountersUseCase
    @Mock
    lateinit var postDecCountersUseCase: PostDecCountersUseCase
    @Mock
    lateinit var deleteCountersUseCase: DeleteCountersUseCase
    @Mock
    lateinit var productsViewDataMapper: ProductsViewDataMapper
    @Mock
    lateinit var counterListViewDataMapper: CounterListViewDataMapper
    @Mock
    lateinit var productsRealmMapper: ProductsRealmMapper
    // endregion

    // region Captors vars
    @Captor
    private lateinit var captorGetProducts: KArgumentCaptor<BaseSubscriber<ProductsViewData, ProductsData>>

    @Captor
    private lateinit var captorGetCounters: KArgumentCaptor<BaseSubscriber<List<CounterViewData?>, List<CounterData?>>>
    // endregion

    private lateinit var shopCartViewModel: ShopCartViewModel

    // region Setup Data

    @Before
    fun setUp() {
        initCaptors()
        initDataMocks()
        initViewModel()
    }

    // region Use Case Execute Testing
    @Test
    fun testGetProductsUseCase() {
        shopCartViewModel.fetchAllProducts(true)
        Mockito.verify(getProductsUseCase, times(1)).execute(any(), anyOrNull())
    }

    @Test
    fun testGetCountersUseCase() {
        shopCartViewModel.fetchAllCounters()
        Mockito.verify(getCountersUseCase, times(1)).execute(any(), anyOrNull())
    }

    @Test
    fun testPostCreateCountersUseCase() {
        shopCartViewModel.hasConnectionLiveData.postValue(true)
        shopCartViewModel.processCounter(
            counterActionView = CounterActionViewData.CREATE,
            request = CounterData(title = "id"),
            liveData = shopCartViewModel.getPostCreateCounterLiveData(),
            isTest = true
        )
        Mockito.verify(postCountersUseCase, times(1)).execute(any(), anyOrNull())
    }

    @Test
    fun testPostIncCountersUseCase() {
        shopCartViewModel.hasConnectionLiveData.postValue(true)
        shopCartViewModel.processCounter(
            counterActionView = CounterActionViewData.INC,
            request = CounterData(id = "asd"),
            liveData = shopCartViewModel.getPostIncCountersLiveData(),
            isTest = true
        )
        Mockito.verify(postIncCountersUseCase, times(1)).execute(any(), anyOrNull())
    }

    @Test
    fun testPostDecCountersUseCase() {
        shopCartViewModel.hasConnectionLiveData.postValue(true)
        shopCartViewModel.processCounter(
            counterActionView = CounterActionViewData.DEC,
            request = CounterData(id = "asd"),
            liveData = shopCartViewModel.getPostDecCountersLiveData(),
            isTest = true
        )
        Mockito.verify(postDecCountersUseCase, times(1)).execute(any(), anyOrNull())
    }

    @Test
    fun testDeleteCountersUseCase() {
        shopCartViewModel.hasConnectionLiveData.postValue(true)
        shopCartViewModel.processCounter(
            counterActionView = CounterActionViewData.DELETE,
            request = CounterData(id = "asd"),
            liveData = shopCartViewModel.getDeleteCounterLiveData(),
            isTest = true
        )
        Mockito.verify(deleteCountersUseCase, times(1)).execute(any(), anyOrNull())
    }
    // endregion

    // region LiveData Status

    @Test
    fun testStatusLiveDataGetProductsCompleteItem_isLoading() {
        shopCartViewModel.let { viewModel ->
            viewModel.fetchAllProducts()

            Mockito.verify(getProductsUseCase, times(1))
                .execute(captorGetProducts.capture(), anyOrNull())

            Assert.assertEquals(true, viewModel.isLoading.get())
            Assert.assertEquals(false, viewModel.isError.get())
            Assert.assertNotNull(viewModel.getProductsLiveData().value)
            Assert.assertEquals(
                ResourceState.LOADING,
                viewModel.getProductsLiveData().value?.status
            )
        }
    }

    @Test
    fun testStatusLiveDataProductsCompleteItem_isError() {
        val methodName = "${object : Any() {}.javaClass.enclosingMethod?.name}()"

        shopCartViewModel.let { viewModel ->
            viewModel.fetchAllProducts(true)

            captorGetProducts.let {
                Mockito.verify(getProductsUseCase, times(1))
                    .execute(it.capture(), anyOrNull())
                it.firstValue.onComplete()
                it.firstValue.onError(getErrorMessage(showErrorMessage(), methodName))
            }

            Assert.assertEquals(false, viewModel.isLoading.get())
            Assert.assertEquals(true, viewModel.isError.get())
            Assert.assertNotNull(viewModel.getProductsLiveData().value)
            Assert.assertEquals(
                ResourceState.ERROR,
                viewModel.getProductsLiveData().value?.status
            )
        }
    }

    @Test
    fun testStatusLiveDataGetCountersCompleteItem_isLoading() {
        shopCartViewModel.let { viewModel ->
            viewModel.fetchAllCounters()

            Mockito.verify(getCountersUseCase, times(1))
                .execute(captorGetCounters.capture(), anyOrNull())

            Assert.assertEquals(false, viewModel.isError.get())
            Assert.assertNotNull(viewModel.getCountersLiveData().value)
            Assert.assertEquals(
                ResourceState.LOADING,
                viewModel.getCountersLiveData().value?.status
            )
        }
    }

    @Test
    fun testStatusLiveDataCountersCompleteItem_isError() {
        val methodName = "${object : Any() {}.javaClass.enclosingMethod?.name}()"

        shopCartViewModel.let { viewModel ->
            viewModel.fetchAllCounters()

            captorGetCounters.let {
                Mockito.verify(getCountersUseCase, times(1))
                    .execute(it.capture(), anyOrNull())
                it.firstValue.onComplete()
                it.firstValue.onError(getErrorMessage(showErrorMessage(), methodName))
            }

            Assert.assertEquals(false, viewModel.isLoading.get())
            Assert.assertEquals(true, viewModel.isError.get())
            Assert.assertNotNull(viewModel.getCountersLiveData().value)
            Assert.assertEquals(
                ResourceState.ERROR,
                viewModel.getCountersLiveData().value?.status
            )
        }
    }
    // endregion
}