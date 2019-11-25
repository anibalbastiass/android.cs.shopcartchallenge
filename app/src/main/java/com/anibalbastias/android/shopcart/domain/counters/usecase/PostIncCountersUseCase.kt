package com.anibalbastias.android.shopcart.domain.counters.usecase

import com.anibalbastias.android.shopcart.domain.base.interactor.FlowableUseCase
import com.anibalbastias.android.shopcart.data.dataStoreFactory.counters.model.CounterData
import com.anibalbastias.android.shopcart.domain.base.executor.APIPostExecutionThread
import com.anibalbastias.android.shopcart.domain.base.executor.APIThreadExecutor
import com.anibalbastias.android.shopcart.domain.counters.repository.ICountersRepository
import io.reactivex.Flowable
import javax.inject.Inject

open class PostIncCountersUseCase @Inject constructor(
    private val countersRepository: ICountersRepository,
    threadExecutor: APIThreadExecutor,
    postExecutionThread: APIPostExecutionThread
) : FlowableUseCase<List<CounterData?>, CounterData?>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: CounterData?): Flowable<List<CounterData?>> =
        countersRepository.postIncCounter(params!!)

}