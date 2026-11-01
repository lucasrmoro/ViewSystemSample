package br.com.viewsystem_sample.structural.designsystem.base.fragment

import android.content.res.Resources.NotFoundException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import br.com.viewsystem_sample.structural.core.R
import br.com.viewsystem_sample.structural.core.base.viewModel.BaseViewModel
import br.com.viewsystem_sample.structural.designsystem.base.activity.BaseContainerActivity
import br.com.viewsystem_sample.structural.di.dependencyViewModelForClass
import br.com.viewsystem_sample.structural.designsystem.ext.getViewModelClass
import br.com.viewsystem_sample.structural.designsystem.ext.onGetAndroidWindowInsets

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(
    private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {

    private val baseContainerActivity: BaseContainerActivity<*>?
        get() = activity as? BaseContainerActivity<*>

    private var _binding: VB? = null
    protected val binding
        get() = _binding ?: throw NotFoundException("ViewBinding not found")

    protected val viewModel by dependencyViewModelForClass(getViewModelClass())
    protected val genericErrorMessage
        get() = getString(R.string.an_error_occurred_try_again_later)

    protected abstract fun setupViews()
    protected open fun setupEdgeToEdge() {}
    protected open fun setupListeners() {}
    protected open fun setupObservers() {}

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflater(inflater, container, false)
        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupEdgeToEdge()
    }

    @CallSuper
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    protected fun popBackStack() {
        findNavController().popBackStack()
    }

    protected fun showScreenLoading() {
        baseContainerActivity?.showScreenLoading()
    }

    protected fun dismissScreenLoading() {
        baseContainerActivity?.dismissScreenLoading()
    }

    protected fun onGetAndroidWindowInsets(block: (top: Int, bottom: Int, left: Int, right: Int) -> Unit) {
        binding.root.onGetAndroidWindowInsets(block)
    }
}
