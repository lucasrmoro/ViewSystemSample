package br.com.viewsystem_sample.core_ui.ext

import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import br.com.viewsystem_sample.core_ui.R

fun NavController.navigateAnimated(@IdRes actionResId: Int) {
    navigate(
        actionResId,
        bundleOf(),
        NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_from_right)
            .setExitAnim(R.anim.slide_out_to_left)
            .setPopEnterAnim(R.anim.slide_in_from_left)
            .setPopExitAnim(R.anim.slide_out_to_right)
            .build()
    )
}