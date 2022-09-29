package com.ejc.data.remote.provider

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ProviderRequestImpl: ProviderRequest {
    override fun client(): OkHttpClient.Builder {
       return OkHttpClient.Builder().apply {
           connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
           readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
           writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
       }
    }

    override fun http(url: String, interceptor: Interceptor?): Retrofit {
        val client = this.client()

        interceptor?.run {
            client.addInterceptor(this)
        }

        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
    }

    companion object {
        private const val CONNECTION_TIME_OUT = 30L
        private const val READ_TIME_OUT = 30L
        private const val WRITE_TIME_OUT = 30L
    }
}