package br.com.viewsystem_sample.structural.designsystem.base.activity

import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import br.com.viewsystem_sample.structural.core.base.viewModel.BaseViewModel
import br.com.viewsystem_sample.structural.core.ext.hideKeyboard
import br.com.viewsystem_sample.structural.core.provider.appState.AppState
import br.com.viewsystem_sample.structural.core.provider.appState.AppStateProvider
import br.com.viewsystem_sample.structural.core.provider.permission.PermissionsProvider
import br.com.viewsystem_sample.structural.designsystem.ext.getViewModelClass
import br.com.viewsystem_sample.structural.logger.Logger
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import org.koin.core.parameter.parametersOf

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    private val logger by inject<Logger>()

    private val appStateProvider by inject<AppStateProvider>()
    private val permissionsProvider by inject<PermissionsProvider> {
        parametersOf(this)
    }
    protected abstract val binding: ViewBinding
    protected val viewModel: VM by lazy { viewModelForClass(getViewModelClass()).value }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        setupObservers()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionsProvider.onRequestPermissionsResult(
            requestCode,
            permissions.asList(),
            grantResults
        )
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            currentFocus.takeIf { it is EditText }?.run {
                val outRect = Rect()
                getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    hideKeyboard()
                    clearFocus()
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    protected open fun onAppStateChanged(state: AppState) {
        logger.debug("App state: $state")
    }

    private fun setupObservers() {
        appStateProvider.state.observe(this, ::onAppStateChanged)
    }

}
