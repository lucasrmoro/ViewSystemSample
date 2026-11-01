package br.com.viewsystem_sample.structural.permissions

import androidx.annotation.MainThread
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import android.content.Context

internal class PermissionsProviderImpl private constructor(
    context: Context,
    private val activity: ComponentActivity?
) : PermissionsProvider {

    constructor(activity: ComponentActivity) : this(activity.applicationContext, activity)

    constructor(context: Context) : this(context.applicationContext, null)

    private val permissionMapper = AndroidPermissionMapper(context)

    private val pendingRequests = mutableListOf<PermissionRequest>()

    private val launcher = activity?.registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        val request = pendingRequests.firstOrNull() ?: return@registerForActivityResult
        val permission = request.currentPermission ?: return@registerForActivityResult
        request.recordResult(
            permissionMapper.toResult(
                requireNotNull(activity),
                permission,
                results
            )
        )
        launchNextPermission()
    }

    override fun isGranted(permission: Permission): Boolean = permissionMapper.isGranted(permission)

    @MainThread
    override fun requestPermissions(
        vararg permissions: Permission,
        block: (results: Map<Permission, PermissionResult>) -> Unit
    ) {
        checkNotNull(activity) { "Permission requests require a ComponentActivity" }
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
                    checkNotNull(launcher) { "Permission requests require a ComponentActivity" }
                        .launch(arrayOf(name))
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
