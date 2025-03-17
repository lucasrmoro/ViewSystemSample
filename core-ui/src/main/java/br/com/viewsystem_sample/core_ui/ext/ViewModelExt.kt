package br.com.viewsystem_sample.core_ui.ext

import br.com.viewsystem_sample.core_ui.base.activity.BaseActivity
import br.com.viewsystem_sample.core.base.viewModel.BaseViewModel
import br.com.viewsystem_sample.core.ext.ONE
import br.com.viewsystem_sample.core_ui.base.fragment.BaseFragment
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
internal fun <VM : BaseViewModel> BaseFragment<*, VM>.getViewModelClass(): KClass<VM> =
    ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[Int.ONE] as Class<VM>).kotlin

@Suppress("UNCHECKED_CAST")
internal fun <VM : BaseViewModel> BaseActivity<VM>.getViewModelClass(): KClass<VM> =
    ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.first() as Class<VM>).kotlin