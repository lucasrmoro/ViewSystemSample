package br.com.viewsystem_sample.structural.core.ext

import br.com.viewsystem_sample.structural.core.di.DispatcherDefault
import br.com.viewsystem_sample.structural.core.di.DispatcherIO
import br.com.viewsystem_sample.structural.core.di.DispatcherMain
import kotlinx.coroutines.CoroutineDispatcher
import br.com.viewsystem_sample.structural.di.DependencyResolver
import br.com.viewsystem_sample.structural.di.get

val DependencyResolver.dispatcherIO: CoroutineDispatcher
    get() = get(DispatcherIO)

val DependencyResolver.dispatcherMain: CoroutineDispatcher
    get() = get(DispatcherMain)

val DependencyResolver.dispatcherDefault: CoroutineDispatcher
    get() = get(DispatcherDefault)
