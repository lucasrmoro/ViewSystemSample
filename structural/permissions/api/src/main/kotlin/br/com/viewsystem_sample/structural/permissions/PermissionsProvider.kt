package br.com.viewsystem_sample.structural.permissions

interface PermissionsProvider {

    /** Checks whether a permission is granted without showing a permission prompt. */
    fun isGranted(permission: Permission): Boolean

    fun requestPermissions(
        vararg permissions: Permission,
        block: (results: Map<Permission, PermissionResult>) -> Unit
    )

    fun requestCameraPermission(block: (result: PermissionResult) -> Unit)

    fun requestNotificationsPermission(block: (result: PermissionResult) -> Unit)

}
