package br.com.viewsystem_sample.structural.permissions

import androidx.annotation.MainThread
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts

internal class PermissionsProviderImpl(
    activity: ComponentActivity
) : PermissionsProvider {

    private val permissionMapper = AndroidPermissionMapper(activity)

    private val pendingRequests = mutableListOf<PermissionRequest>()

    private val launcher =
        activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
            val request = pendingRequests.firstOrNull() ?: return@registerForActivityResult
            val permission = request.currentPermission ?: return@registerForActivityResult
            request.recordResult(permissionMapper.toResult(permission, results))
            launchNextPermission()
        }

    @MainThread
    override fun requestPermissions(
        vararg permissions: Permission,
        block: (results: Map<Permission, PermissionResult>) -> Unit
    ) {
        pendingRequests += PermissionRequest(permissions.toList(), block)
        if (pendingRequests.size == 1) {
            launchNextPermission()
        }
    }

    @MainThread
    override fun requestCameraPermission(block: (result: PermissionResult) -> Unit) {
        requestPermissions(Permission.CAMERA) { block(it.getValue(Permission.CAMERA)) }
    }

    @MainThread
    override fun requestNotificationsPermission(block: (result: PermissionResult) -> Unit) {
        requestPermissions(Permission.NOTIFICATIONS) { block(it.getValue(Permission.NOTIFICATIONS)) }
    }

    private fun launchNextPermission() {
        while (pendingRequests.isNotEmpty()) {
            val request = pendingRequests.first()
            val permission = request.currentPermission
            when {
                permission != null -> permissionMapper.runtimeName(permission)?.let { name ->
                    launcher.launch(arrayOf(name))
                    return
                } ?: request.recordResult(PermissionResult.NOT_REQUIRED)

                else -> completeRequest(request)
            }
        }
    }

    private fun completeRequest(request: PermissionRequest) {
        try {
            request.complete()
        } finally {
            pendingRequests.removeAt(0)
        }
    }

}
