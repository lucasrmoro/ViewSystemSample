package br.com.viewsystem_sample.structural.designsystem.base.activity

import android.os.Bundle
import androidx.annotation.NavigationRes
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import br.com.viewsystem_sample.structural.core.base.viewModel.BaseViewModel
import br.com.viewsystem_sample.structural.core.ext.ZERO
import br.com.viewsystem_sample.structural.designsystem.R
import br.com.viewsystem_sample.structural.designsystem.databinding.ActivityBaseContainerBinding
import br.com.viewsystem_sample.structural.designsystem.ext.hide
import br.com.viewsystem_sample.structural.designsystem.ext.onGetAndroidWindowInsets
import br.com.viewsystem_sample.structural.designsystem.ext.setStatusBarColorCompat
import br.com.viewsystem_sample.structural.designsystem.ext.show
import br.com.viewsystem_sample.structural.designsystem.ext.viewBinding

abstract class BaseContainerActivity<VM : BaseViewModel> : BaseActivity<VM>() {

    override val binding by viewBinding(ActivityBaseContainerBinding::inflate)
    private lateinit var navController: NavController

    @NavigationRes
    abstract fun navigationRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavigation()
        setupToolbar()
        setupEdgeToEdge()
        if (isTaskRoot && isFirstFragmentOfNavGraph()) {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }

    fun showScreenLoading() {
        binding.pbLoading.show()
    }

    fun dismissScreenLoading() {
        binding.pbLoading.hide()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        navController = navHostFragment.navController
        navController.setGraph(navigationRes())
        navController.addOnDestinationChangedListener { _, _, _ ->
            supportActionBar?.setDisplayHomeAsUpEnabled(
                supportFragmentManager.backStackEntryCount > Int.ZERO || !isFirstFragmentOfNavGraph()
            )
        }
    }

    private fun setupToolbar() = with(binding.toolbar) {
        setSupportActionBar(androidToolbar)
        title = null
        setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupEdgeToEdge() = with(binding) {
        navHostFragment.onGetAndroidWindowInsets { top, bottom, left, right ->
            toolbar.applyWindowInsets(top = top, left = left, right = right)
        }
    }

    private fun isFirstFragmentOfNavGraph(): Boolean {
        val currentFragmentId = navController.currentDestination?.id
        val navGraphStartFragmentId = navController.graph.startDestinationId
        return navGraphStartFragmentId == currentFragmentId
    }
}