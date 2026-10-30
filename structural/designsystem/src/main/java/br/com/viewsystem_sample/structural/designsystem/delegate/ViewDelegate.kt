package br.com.viewsystem_sample.structural.designsystem.delegate

import android.view.View
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ViewDelegate<T : View>(private val view: T) : ReadOnlyProperty<View, T> {

    override fun getValue(thisRef: View, property: KProperty<*>): T = view

}