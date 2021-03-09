package com.softwareuk.di

import android.content.Context
import com.softwareuk.common.koin.InjectionModule
import com.softwareuk.interactor.AssetGateway
import com.softwareuk.interactor.MainInteractor
import com.softwareuk.ui.candle.CandlePresenter
import com.softwareuk.ui.performance.PerformancePresenter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object MainModule : InjectionModule {
    override fun create(): Module {
        return module {
            single { MainInteractor(get()) }
            single { AssetGateway(get()) }
            single { get<Context>().assets }
            viewModel { PerformancePresenter(get()) }
            viewModel { CandlePresenter(get()) }
        }
    }
}