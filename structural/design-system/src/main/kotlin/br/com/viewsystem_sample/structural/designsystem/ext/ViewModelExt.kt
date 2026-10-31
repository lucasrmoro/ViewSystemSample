package br.com.viewsystem_sample.structural.designsystem.ext

import br.com.viewsystem_sample.structural.designsystem.base.activity.BaseActivity
import br.com.viewsystem_sample.structural.core.base.viewModel.BaseViewModel
import br.com.viewsystem_sample.structural.core.ext.ONE
import br.com.viewsystem_sample.structural.designsystem.base.fragment.BaseFragment
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
internal fun <VM : BaseViewModel> BaseFragment<*, VM>.getViewModelClass(): KClass<VM> =
    ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[Int.ONE] as Class<VM>).kotlin

@Suppress("UNCHECKED_CAST")
internal fun <VM : BaseViewModel> BaseActivity<VM>.getViewModelClass(): KClass<VM> =
    ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.first() as Class<VM>).kotlin