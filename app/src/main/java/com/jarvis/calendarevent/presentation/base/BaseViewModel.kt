package com.jarvis.calendarevent.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jarvis.calendarevent.common.LOADING
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    val mLoading = MutableLiveData<LOADING>()

    val mError = MutableLiveData<Throwable>()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val job: Job = SupervisorJob()

    override fun onCleared() {
        super.onCleared()
        job.cancel() // huỷ bỏ job
    }
}