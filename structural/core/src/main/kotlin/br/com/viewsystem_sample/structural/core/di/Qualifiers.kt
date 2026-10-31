package br.com.viewsystem_sample.structural.core.di

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue

object DispatcherIO : Qualifier {

    override val value: QualifierValue = "DispatcherIO"

}

object DispatcherMain : Qualifier {

    override val value: QualifierValue = "DispatcherMain"

}

object DispatcherDefault : Qualifier {

    override val value: QualifierValue = "DispatcherDefault"

}