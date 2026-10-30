package br.com.viewsystem_sample.structural.permissions

interface PermissionsProvider {

    fun requestPermissions(
        vararg permissions: Permission,
        block: (results: Map<Permission, PermissionResult>) -> Unit
    )

    fun requestCameraPermission(block: (result: PermissionResult) -> Unit)

    fun requestNotificationsPermission(block: (result: PermissionResult) -> Unit)

}
