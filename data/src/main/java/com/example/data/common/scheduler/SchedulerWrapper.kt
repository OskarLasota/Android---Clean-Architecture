package com.example.data.common.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerWrapper : AppScheduler {

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun mainThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    companion object {
        private val schedulerWrapper: SchedulerWrapper = SchedulerWrapper()

        fun getInstance(): SchedulerWrapper = schedulerWrapper
    }
}