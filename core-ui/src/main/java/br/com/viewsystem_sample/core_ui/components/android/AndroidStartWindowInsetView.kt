package br.com.viewsystem_sample.core_ui.components.android

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import br.com.viewsystem_sample.core_ui.databinding.AndroidStartWindowInsetPreviewBinding
import br.com.viewsystem_sample.core_ui.ext.onGetAndroidStartWindowInset

class AndroidStartWindowInsetView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    init {
        if (isInEditMode) {
            AndroidStartWindowInsetPreviewBinding.inflate(
                LayoutInflater.from(context),
                this,
                true
            )
        } else {
            onGetAndroidStartWindowInset {
                layoutParams.height = it
            }
        }
    }

}