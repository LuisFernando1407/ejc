package com.ejc.di

import org.koin.core.module.Module

abstract class AppDependencyInjection {
    abstract fun all(): List<Module>
}