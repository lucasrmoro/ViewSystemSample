package br.com.viewsystem_sample.structural.logger

interface Logger {

    fun debug(message: String)

    fun error(throwable: Throwable)

}
