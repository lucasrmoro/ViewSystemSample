package br.com.viewsystem_sample.core_ui.adapter.model

import br.com.viewsystem_sample.core_ui.adapter.model.base.AdapterItem
import br.com.viewsystem_sample.core_ui.adapter.model.base.AdapterViewType
import br.com.viewsystem_sample.core.ext.ZERO

data class Foo(
    val id: Long = Long.ZERO,
    val msg: String
) : AdapterItem {

    override val itemViewType = AdapterViewType.FOO

    override fun areItemsTheSame(toCompare: Any): Boolean =
        this.id == (toCompare as? Foo)?.id

}