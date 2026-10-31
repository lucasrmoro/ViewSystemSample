package br.com.viewsystem_sample.structural.permissions

import androidx.activity.ComponentActivity

fun createPermissionsProvider(activity: ComponentActivity): PermissionsProvider = PermissionsProviderImpl(activity)
