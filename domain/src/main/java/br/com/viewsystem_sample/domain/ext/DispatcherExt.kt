package br.com.viewsystem_sample.domain.ext

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

suspend fun <T> CoroutineDispatcher.execute(block: suspend CoroutineScope.() -> T) =
    withContext(this, block)