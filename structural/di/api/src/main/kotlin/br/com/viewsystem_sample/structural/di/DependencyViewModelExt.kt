package br.com.viewsystem_sample.structural.di

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import kotlin.reflect.KClass

fun <T : ViewModel> ComponentActivity.dependencyViewModelForClass(type: KClass<T>): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE) { get<DependencyViewModelProvider>().get(this, type) }

fun <T : ViewModel> Fragment.dependencyViewModelForClass(type: KClass<T>): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE) { get<DependencyViewModelProvider>().get(this, type) }
