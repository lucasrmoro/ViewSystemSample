package br.com.viewsystem_sample.core_ui.components.android

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import br.com.viewsystem_sample.core_ui.databinding.AndroidBottomWindowInsetPreviewBinding
import br.com.viewsystem_sample.core_ui.ext.onGetAndroidBottomWindowInset

class AndroidBottomWindowInsetView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    init {
        if (isInEditMode) {
            AndroidBottomWindowInsetPreviewBinding.inflate(
                LayoutInflater.from(context),
                this,
                true
            )
        } else {
            onGetAndroidBottomWindowInset {
                layoutParams.height = it
            }
        }
    }

}