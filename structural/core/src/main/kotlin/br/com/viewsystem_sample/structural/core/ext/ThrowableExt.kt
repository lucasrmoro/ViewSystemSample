package br.com.viewsystem_sample.structural.core.ext

import br.com.viewsystem_sample.structural.network.exception.ApiException
import br.com.viewsystem_sample.structural.network.exception.HttpException
import br.com.viewsystem_sample.structural.network.exception.SampleException

internal fun Throwable?.toSampleException() = SampleException(
    message = this?.message.orEmpty(),
    apiException = (this as? HttpException)?.apiException ?: ApiException()
)
