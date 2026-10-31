package br.com.viewsystem_sample.structural.core.ext

import br.com.viewsystem_sample.structural.core.di.DispatcherDefault
import br.com.viewsystem_sample.structural.core.di.DispatcherIO
import br.com.viewsystem_sample.structural.core.di.DispatcherMain
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.scope.Scope

val Scope.dispatcherIO: CoroutineDispatcher
    get() = get(DispatcherIO)

val Scope.dispatcherMain: CoroutineDispatcher
    get() = get(DispatcherMain)

val Scope.dispatcherDefault: CoroutineDispatcher
    get() = get(DispatcherDefault)