package br.com.viewsystem_sample.core.ext

import br.com.viewsystem_sample.entities.ApiException
import br.com.viewsystem_sample.entities.SampleException
import com.google.gson.Gson
import retrofit2.HttpException

internal fun Throwable?.toSampleException() = SampleException(
    message = this?.message.orEmpty(),
    apiException = (this as? HttpException).toApiException()
)

private fun HttpException?.toApiException() = runCatching {
    Gson().fromJson(this?.response()?.errorBody()?.string().orEmpty(), ApiException::class.java)
}.getOrElse { ApiException() }

