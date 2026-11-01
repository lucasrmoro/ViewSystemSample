package br.com.viewsystem_sample.structural.core.di

import br.com.viewsystem_sample.structural.di.DependencyQualifier

object DispatcherIO : DependencyQualifier {

    override val value: String = "DispatcherIO"

}

object DispatcherMain : DependencyQualifier {

    override val value: String = "DispatcherMain"

}

object DispatcherDefault : DependencyQualifier {

    override val value: String = "DispatcherDefault"

}
