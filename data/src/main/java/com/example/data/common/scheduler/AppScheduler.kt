package com.example.data.common.scheduler

import io.reactivex.Scheduler

interface AppScheduler {
    fun io(): Scheduler
    fun mainThread(): Scheduler
}