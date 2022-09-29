package com.ejc.data.remote.service

import com.ejc.data.remote.provider.ProviderRequest

class AppServiceGenerator(private val provider: ProviderRequest) {
    fun <S> create(settings: AppServiceSettings<S>): S = this.provider.http(
        url = settings.env.url,
        interceptor = settings.interceptor
    ).create(settings.service)
}