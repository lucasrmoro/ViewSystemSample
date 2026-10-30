package br.com.viewsystem_sample.structural.designsystem.ext

import android.view.View
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsCompat.Type.InsetsType
import br.com.viewsystem_sample.structural.core.ext.ZERO
import br.com.viewsystem_sample.structural.core.ext.orZero

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun View?.hide() {
    this?.visibility = View.GONE
}

fun View?.invisible() {
    this?.visibility = View.INVISIBLE
}

fun View.onGetAndroidWindowInsets(block: (top: Int, bottom: Int, left: Int, right: Int) -> Unit) {
    onGetAndroidWindowInsets(insetsType = WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout() or WindowInsetsCompat.Type.ime()) {
        block(it?.top.orZero(), it?.bottom.orZero(), it?.left.orZero(), it?.right.orZero())
    }
}

private fun View.onGetAndroidWindowInsets(
    @InsetsType insetsType: Int,
    block: (Insets?) -> Unit
) {
    ViewCompat.setOnApplyWindowInsetsListener(this) { _, insets ->
        block(insets.getInsets(insetsType))
        insets
    }
}

fun View.sumPadding(
    top: Int = Int.ZERO,
    bottom: Int = Int.ZERO,
    left: Int = Int.ZERO,
    right: Int = Int.ZERO
) {
    setPadding(
        paddingLeft.plus(left),
        paddingTop.plus(top),
        paddingRight.plus(right),
        paddingBottom.plus(bottom)
    )
}