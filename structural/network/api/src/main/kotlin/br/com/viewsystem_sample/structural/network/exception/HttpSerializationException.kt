package br.com.viewsystem_sample.structural.network.exception

/** A JSON request or response could not be converted to the expected model. */
class HttpSerializationException(cause: Throwable) : Exception("HTTP JSON conversion failed", cause)
