package br.com.viewsystem_sample.core_ui.components.android

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import br.com.viewsystem_sample.core_ui.databinding.AndroidEndWindowInsetPreviewBinding
import br.com.viewsystem_sample.core_ui.ext.onGetAndroidEndWindowInset

class AndroidEndWindowInsetView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    init {
        if (isInEditMode) {
            AndroidEndWindowInsetPreviewBinding.inflate(
                LayoutInflater.from(context),
                this,
                true
            )
        } else {
            onGetAndroidEndWindowInset {
                layoutParams.height = it
            }
        }
    }

}