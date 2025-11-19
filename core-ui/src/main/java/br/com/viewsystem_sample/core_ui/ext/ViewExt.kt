package br.com.viewsystem_sample.core_ui.ext

import android.view.View
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsCompat.Type.InsetsType
import br.com.viewsystem_sample.core.ext.ZERO
import br.com.viewsystem_sample.core.ext.orZero

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun View?.hide() {
    this?.visibility = View.GONE
}

fun View?.invisible() {
    this?.visibility = View.INVISIBLE
}

fun View.onGetAndroidTopWindowInset(block: View.(size: Int) -> Unit) {
    onGetAndroidWindowInsets(
        insetsType = WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime()
    ) {
        block(this, it?.top.orZero())
    }
}

fun View.onGetAndroidBottomWindowInset(block: View.(size: Int) -> Unit) {
    onGetAndroidWindowInsets(insetsType = WindowInsetsCompat.Type.systemBars()) {
        block(this, it?.bottom.orZero())
    }
}

fun View.onGetAndroidStartWindowInset(block: View.(size: Int) -> Unit) {
    onGetAndroidWindowInsets(insetsType = WindowInsetsCompat.Type.systemBars()) {
        block(this, it?.left.orZero())
    }
}

fun View.onGetAndroidEndWindowInset(block: View.(size: Int) -> Unit) {
    onGetAndroidWindowInsets(insetsType = WindowInsetsCompat.Type.systemBars()) {
        block(this, it?.right.orZero())
    }
}

private fun View.onGetAndroidWindowInsets(
    @InsetsType insetsType: Int,
    block: View.(Insets?) -> Unit
) {
    ViewCompat.setOnApplyWindowInsetsListener(this) { _, insets ->
        block(this, insets.getInsets(insetsType))
        insets
    }
}

fun View.sumPadding(
    top: Int = Int.ZERO,
    bottom: Int = Int.ZERO,
    start: Int = Int.ZERO,
    end: Int = Int.ZERO
) {
    setPadding(
        paddingLeft.plus(start),
        paddingTop.plus(top),
        paddingRight.plus(end),
        paddingBottom.plus(bottom)
    )
}