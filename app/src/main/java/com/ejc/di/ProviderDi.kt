package com.ejc.di

import org.koin.core.module.Module

object ProviderDi: ArrayList<Module>() {

    fun addModule(module: Module): ProviderDi {
        this.add(module)
        return this
    }

    fun addAllModules(modules: List<Module>): ProviderDi {
        this.addAll(modules)
        return this
    }

    fun build() = this
}