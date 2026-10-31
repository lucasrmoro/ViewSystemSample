package br.com.viewsystem_sample.structural.logger

internal class LoggerImpl : Logger {

    override fun debug(message: String) = Unit

    override fun error(throwable: Throwable) = Unit

}
