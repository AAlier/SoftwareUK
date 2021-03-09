package com.softwareuk.common.koin

import org.koin.core.module.Module

interface InjectionModule {
    fun create(): Module
}
