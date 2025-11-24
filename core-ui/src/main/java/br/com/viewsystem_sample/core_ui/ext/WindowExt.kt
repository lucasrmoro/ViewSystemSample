package br.com.viewsystem_sample.core_ui.ext

import android.os.Build
import android.view.Window
import android.view.WindowInsets
import androidx.annotation.ColorInt
import androidx.core.view.updatePadding

@Suppress("DEPRECATION")
fun Window.setStatusBarColorCompat(@ColorInt color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
        decorView.setOnApplyWindowInsetsListener { view, insets ->
            val statusBarInsets = insets.getInsets(WindowInsets.Type.statusBars())
            view.setBackgroundColor(color)
            view.updatePadding(top = statusBarInsets.top)
            insets
        }
    } else {
        statusBarColor = color
    }
}