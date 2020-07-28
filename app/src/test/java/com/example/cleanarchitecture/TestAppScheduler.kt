package com.example.cleanarchitecture

import com.example.data.common.scheduler.AppScheduler
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers


class TestAppScheduler: AppScheduler {
    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun mainThread(): Scheduler {
        return Schedulers.trampoline()
    }
}