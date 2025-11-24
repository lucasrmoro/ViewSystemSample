package br.com.viewsystem_sample.core_ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.view.MenuProvider
import androidx.core.view.updatePadding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import br.com.viewsystem_sample.core_ui.databinding.SampleToolbarBinding
import br.com.viewsystem_sample.core_ui.delegate.ViewDelegate

class SampleToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : CardView(context, attrs) {

    private val binding =
        SampleToolbarBinding.inflate(LayoutInflater.from(context), this, true)

    val androidToolbar by ViewDelegate(binding.androidToolbar)

    fun setupWithNavController(
        navController: NavController,
        configuration: AppBarConfiguration = AppBarConfiguration(navController.graph)
    ) {
        androidToolbar.setupWithNavController(navController, configuration)
    }

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