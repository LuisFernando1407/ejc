package com.ejc.di.network

import com.ejc.data.remote.provider.ProviderRequest
import com.ejc.data.remote.provider.ProviderRequestImpl
import com.ejc.data.remote.service.AppServiceGenerator
import com.ejc.di.AppDependencyInjection
import org.koin.core.module.Module
import org.koin.dsl.module

object AppNetwork: AppDependencyInjection() {
    override fun all() = arrayListOf<Module>().apply {
        add(
            module {
                single<ProviderRequest> { ProviderRequestImpl() }
                single { AppServiceGenerator(provider = get()) }
            }
        )
    }
}