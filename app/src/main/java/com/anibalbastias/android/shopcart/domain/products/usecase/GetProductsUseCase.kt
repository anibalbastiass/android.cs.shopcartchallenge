package com.anibalbastias.android.shopcart.domain.products.usecase

import com.anibalbastias.android.shopcart.domain.base.interactor.FlowableUseCase
import com.anibalbastias.android.shopcart.data.dataStoreFactory.products.model.ProductsData
import com.anibalbastias.android.shopcart.domain.base.executor.APIPostExecutionThread
import com.anibalbastias.android.shopcart.domain.base.executor.APIThreadExecutor
import com.anibalbastias.android.shopcart.domain.products.repository.IProductsRepository
import io.reactivex.Flowable
import javax.inject.Inject

open class GetProductsUseCase @Inject constructor(
    private val productsRepository: IProductsRepository,
    threadExecutor: APIThreadExecutor,
    postExecutionThread: APIPostExecutionThread
) : FlowableUseCase<ProductsData, String?>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: String?): Flowable<ProductsData> =
        productsRepository.getProducts()

}