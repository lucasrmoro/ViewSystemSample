# Permissions

`permissions/api` exposes `PermissionsProvider`, `Permission`, and `PermissionResult`.
The contract accepts one or more permissions without exposing Activity or platform APIs.

The app depends on `permissions/di` and loads `PermissionsModule.init()` before
opening screens. This module registers a factory per Activity through the creation
function in `permissions/impl`. The implementation uses the Activity Result API and
must be obtained in `onCreate`, before `STARTED`, as in `BaseActivity`. Activities
are never retained in singletons.

`PermissionsProvider.isGranted()` checks the current grant state without showing a
permission prompt. The application-context binding is intended for checks in
services; permission requests still use the Activity-bound provider.

`requestPermissions(vararg permissions)` preserves argument order and processes
one permission at a time. New requests enter a batch queue; every callback is
preserved, including callbacks that enqueue another request. Results include
`GRANTED`, `DENIED`, `NOT_ASK_AGAIN`, `CANCELLED`, and `NOT_REQUIRED`.

Camera and notification permissions have convenience methods. Notification
permission returns `NOT_REQUIRED` before Android 13. The implementation checks the
OS version directly.
