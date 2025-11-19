package br.com.viewsystem_sample.core_ui.components.android

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import br.com.viewsystem_sample.core_ui.databinding.AndroidTopWindowInsetPreviewBinding
import br.com.viewsystem_sample.core_ui.ext.onGetAndroidTopWindowInset

class AndroidTopWindowInsetView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    init {
        if (isInEditMode) {
            AndroidTopWindowInsetPreviewBinding.inflate(
                LayoutInflater.from(context),
                this,
                true
            )
        } else {
            onGetAndroidTopWindowInset {
                layoutParams.height = it
            }
        }
    }

}