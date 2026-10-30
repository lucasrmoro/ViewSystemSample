package br.com.viewsystem_sample.structural.designsystem.components

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.IntDef
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import br.com.viewsystem_sample.structural.designsystem.delegate.TextViewDelegate
import br.com.viewsystem_sample.structural.designsystem.delegate.ViewDelegate
import br.com.viewsystem_sample.structural.core.ext.getCustomAttributes
import br.com.viewsystem_sample.structural.designsystem.R
import br.com.viewsystem_sample.structural.designsystem.databinding.SampleButtonBinding

class SampleButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding = SampleButtonBinding.inflate(LayoutInflater.from(context), this, true)

    private val root by ViewDelegate(binding.root)
    private val tvText by ViewDelegate(binding.tvText)
    private val pbLoading by ViewDelegate(binding.pbLoading)
    private val llRippleEffect by ViewDelegate(binding.llRippleEffect)

    var text by TextViewDelegate(tvText)
    var isLoading: Boolean
        get() = pbLoading.isVisible
        set(value) {
            pbLoading.isVisible = value
            tvText.isGone = value
        }

    @ButtonType
    var buttonType: Int = GREEN
        set(value) {
            setupStyleBy(value)
            field = value
        }

    init {
        setupViews(attrs)
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        llRippleEffect.setOnClickListener(listener)
    }

    private fun setupViews(attrs: AttributeSet?) {
        attrs?.let {
            context.getCustomAttributes(it, R.styleable.SampleButton) {
                text = getString(R.styleable.SampleButton_android_text)
                buttonType = getInt(R.styleable.SampleButton_buttonType, GREEN)
            }
        }
    }

    private fun setupStyleBy(@ButtonType buttonType: Int) {
        when (buttonType) {
            GREEN -> setupStyle(
                background = R.drawable.bg_button_green,
                textColor = R.color.state_color_dark,
                loadingColor = R.color.dark_500
            )

            OUTLINED_GREEN -> setupStyle(
                background = R.drawable.bg_button_outlined_green,
                textColor = R.color.state_color_green,
                loadingColor = R.color.green_300
            )
        }
    }

    private fun setupStyle(
        @DrawableRes background: Int,
        @ColorRes textColor: Int,
        @ColorRes loadingColor: Int
    ) {
        root.setBackgroundResource(background)
        tvText.setTextColor(context.getColorStateList(textColor))
        pbLoading.indeterminateTintList = ColorStateList.valueOf(context.getColor(loadingColor))
    }

    companion object {
        @IntDef(GREEN, OUTLINED_GREEN)
        @Retention(AnnotationRetention.SOURCE)
        annotation class ButtonType

        const val GREEN = 1
        const val OUTLINED_GREEN = 2
    }
}