package br.com.viewsystem_sample.structural.designsystem.di

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue

object ScreenBiometricProvider : Qualifier {

    override val value: QualifierValue = "ScreenBiometricProvider"

}
