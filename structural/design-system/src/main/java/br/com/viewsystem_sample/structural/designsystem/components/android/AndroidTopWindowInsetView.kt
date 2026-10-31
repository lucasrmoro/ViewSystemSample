package br.com.viewsystem_sample.structural.designsystem.components.android

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.updateLayoutParams
import br.com.viewsystem_sample.structural.design_system.databinding.AndroidTopWindowInsetPreviewBinding
import br.com.viewsystem_sample.structural.designsystem.ext.onGetAndroidWindowInsets

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
            onGetAndroidWindowInsets { top, _, _, _ ->
                updateLayoutParams {
                    height = top
                }
            }
        }
    }

}