package br.com.viewsystem_sample.structural.designsystem.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.MenuProvider
import androidx.core.view.updatePadding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import br.com.viewsystem_sample.structural.designsystem.databinding.SampleToolbarBinding
import br.com.viewsystem_sample.structural.designsystem.delegate.ViewDelegate

class SampleToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding =
        SampleToolbarBinding.inflate(LayoutInflater.from(context), this, true)

    val androidToolbar by ViewDelegate(binding.androidToolbar)

    fun addMenuProvider(
        provider: MenuProvider,
        owner: LifecycleOwner,
        state: Lifecycle.State
    ) {
        androidToolbar.addMenuProvider(provider, owner, state)
    }

    fun setNavigationOnClickListener(onClick: View.() -> Unit) {
        androidToolbar.setNavigationOnClickListener {
            onClick.invoke(it)
        }
    }

    fun applyWindowInsets(top: Int, left: Int, right: Int) {
        binding.root.updatePadding(top = top)
        androidToolbar.updatePadding(left = left, right = right)
    }

}