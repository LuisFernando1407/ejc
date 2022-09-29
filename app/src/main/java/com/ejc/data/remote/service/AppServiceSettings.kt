package com.ejc.data.remote.service

import com.ejc.data.remote.AppEnvironment
import okhttp3.Interceptor

data class AppServiceSettings<S>(
    val env: AppEnvironment = AppEnvironment.API,
    val interceptor: Interceptor? = null,
    val service: Class<S>
)