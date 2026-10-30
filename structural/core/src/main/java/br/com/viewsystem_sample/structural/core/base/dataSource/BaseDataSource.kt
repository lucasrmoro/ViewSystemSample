package br.com.viewsystem_sample.structural.core.base.dataSource

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

abstract class BaseDataSource {

    protected fun <T> call(block: suspend FlowCollector<T>.() -> T) = flow {
        emit(block())
    }

}