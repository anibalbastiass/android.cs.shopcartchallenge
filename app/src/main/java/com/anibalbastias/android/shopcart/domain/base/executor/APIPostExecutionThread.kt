package com.anibalbastias.android.shopcart.domain.base.executor

import io.reactivex.Scheduler

interface APIPostExecutionThread {
    val scheduler: Scheduler
}
