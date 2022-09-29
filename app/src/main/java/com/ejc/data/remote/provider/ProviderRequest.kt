package com.ejc.data.remote.provider

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface ProviderRequest {
    fun client(): OkHttpClient.Builder
    fun http(url: String, interceptor: Interceptor?): Retrofit
}