package br.com.viewsystem_sample.structural.permissions

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.ComponentActivity

internal class AndroidPermissionMapper(
    private val activity: ComponentActivity
) {

    @SuppressLint("InlinedApi")
    fun runtimeName(permission: Permission): String? = when (permission) {
        Permission.CAMERA -> Manifest.permission.CAMERA
        Permission.NOTIFICATIONS -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.POST_NOTIFICATIONS
        } else {
            null
        }
    }

    fun toResult(permission: Permission, results: Map<String, Boolean>): PermissionResult {
        val name = runtimeName(permission) ?: return PermissionResult.NOT_REQUIRED
        return when (results[name]) {
            true -> PermissionResult.GRANTED
            false -> if (activity.shouldShowRequestPermissionRationale(name)) {
                PermissionResult.DENIED
            } else {
                PermissionResult.NOT_ASK_AGAIN
            }
            null -> PermissionResult.CANCELLED
        }
    }

}
