package com.ejc.flow.main

import com.ejc.di.AppDependencyInjection
import com.ejc.flow.main.repository.MainRepository
import com.ejc.flow.main.repository.MainRepositoryImpl
import com.ejc.flow.main.usecase.MainUseCase
import com.ejc.flow.main.usecase.MainUseCaseImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class MainModule: AppDependencyInjection() {
    override fun all() = arrayListOf<Module>().apply {
        add(
            module {
                single<MainRepository> { MainRepositoryImpl(service = get()) }

                single<MainUseCase> { MainUseCaseImpl(repo = get()) }

                viewModel {
                    MainViewModel(useCase = get())
                }
            }
        )
    }
}