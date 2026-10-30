package br.com.viewsystem_sample.structural.core.ext

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity.OVERRIDE_TRANSITION_CLOSE

fun Activity.launchActivity(
    intent: Intent,
    showActivityTransition: Boolean = true,
    finishCurrentActivity: Boolean = false
) {
    startActivity(intent)

    if (finishCurrentActivity) finish()
    if (showActivityTransition.not()) stopPendingTransition()
}

@Suppress("DEPRECATION")
private fun Activity.stopPendingTransition() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        overrideActivityTransition(
            OVERRIDE_TRANSITION_CLOSE,
            OVERRIDE_TRANSITION_CLOSE,
            OVERRIDE_TRANSITION_CLOSE
        )
    } else {
        overridePendingTransition(Int.ZERO, Int.ZERO)
    }
}