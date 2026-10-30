package br.com.viewsystem_sample.structural.designsystem.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.viewsystem_sample.structural.designsystem.delegate.EditTextDelegate
import br.com.viewsystem_sample.structural.designsystem.delegate.EditTextHintDelegate
import br.com.viewsystem_sample.structural.designsystem.delegate.EditTextInputTypeDelegate
import br.com.viewsystem_sample.structural.designsystem.delegate.EditTextMaxLengthDelegate
import br.com.viewsystem_sample.structural.designsystem.delegate.TextViewDelegate
import br.com.viewsystem_sample.structural.designsystem.delegate.ViewDelegate
import br.com.viewsystem_sample.structural.core.ext.getCustomAttributes
import br.com.viewsystem_sample.structural.designsystem.R
import br.com.viewsystem_sample.structural.designsystem.databinding.SampleTextFieldBinding

class SampleTextField @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding =
        SampleTextFieldBinding.inflate(LayoutInflater.from(context), this, true)

    private val tvHeader by ViewDelegate(binding.tvHeader)
    private val textInputLayout by ViewDelegate(binding.textInputLayout)
    private val editText by ViewDelegate(binding.editText)

    var text by EditTextDelegate(editText)
    var header by TextViewDelegate(tvHeader)
    var hint by EditTextHintDelegate(editText)
    var maxLength by EditTextMaxLengthDelegate(editText)
    var inputType by EditTextInputTypeDelegate(editText)

    init {
        setupViews(attrs)
    }

    private fun setupViews(attrs: AttributeSet?) {
        attrs?.let {
            context.getCustomAttributes(it, R.styleable.SampleTextField) {
                inputType = getInt(
                    R.styleable.SampleTextField_inputType,
                    EditTextInputTypeDelegate.NONE
                )
                header = getString(R.styleable.SampleTextField_header)
                hint = getString(R.styleable.SampleTextField_android_hint)
                maxLength = getInt(R.styleable.SampleTextField_android_maxLength, Int.MAX_VALUE)
            }
        }
    }

}