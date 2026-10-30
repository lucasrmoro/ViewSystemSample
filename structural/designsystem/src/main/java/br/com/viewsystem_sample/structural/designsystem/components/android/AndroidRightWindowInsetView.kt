package br.com.viewsystem_sample.structural.designsystem.components.android

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.updateLayoutParams
import br.com.viewsystem_sample.structural.designsystem.databinding.AndroidRightWindowInsetPreviewBinding
import br.com.viewsystem_sample.structural.designsystem.ext.onGetAndroidWindowInsets

class AndroidRightWindowInsetView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    init {
        if (isInEditMode) {
            AndroidRightWindowInsetPreviewBinding.inflate(
                LayoutInflater.from(context),
                this,
                true
            )
        } else {
            onGetAndroidWindowInsets { _, _, _, right ->
                updateLayoutParams {
                    width = right
                }
            }
        }
    }

}