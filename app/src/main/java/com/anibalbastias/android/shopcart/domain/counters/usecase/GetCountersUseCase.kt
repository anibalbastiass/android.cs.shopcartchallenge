package com.anibalbastias.android.shopcart.domain.counters.usecase

import com.anibalbastias.android.shopcart.domain.base.interactor.FlowableUseCase
import com.anibalbastias.android.shopcart.data.dataStoreFactory.counters.model.CounterData
import com.anibalbastias.android.shopcart.domain.base.executor.APIPostExecutionThread
import com.anibalbastias.android.shopcart.domain.base.executor.APIThreadExecutor
import com.anibalbastias.android.shopcart.domain.counters.repository.ICountersRepository
import io.reactivex.Flowable
import javax.inject.Inject

open class GetCountersUseCase @Inject constructor(
    private val countersRepository: ICountersRepository,
    threadExecutor: APIThreadExecutor,
    postExecutionThread: APIPostExecutionThread
) : FlowableUseCase<List<CounterData?>, String?>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: String?): Flowable<List<CounterData?>> =
        countersRepository.getCounters()

}