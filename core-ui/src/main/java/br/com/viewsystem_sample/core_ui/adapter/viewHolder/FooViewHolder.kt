package br.com.viewsystem_sample.core_ui.adapter.viewHolder

import br.com.viewsystem_sample.core_ui.adapter.base.BaseViewHolder
import br.com.viewsystem_sample.core_ui.adapter.callbacks.FooAdapterCallbacksSample
import br.com.viewsystem_sample.core_ui.adapter.model.Foo
import br.com.viewsystem_sample.core_ui.databinding.RvFooItemBinding

internal class FooViewHolder(
    binding: RvFooItemBinding,
    private val callbacks: FooAdapterCallbacksSample?
) : BaseViewHolder<Foo, RvFooItemBinding>(binding) {

    override fun onBind(item: Foo) = bind {
        root.setOnClickListener {
            callbacks?.onFooClicked(item)
        }
        tvMsg.text = item.msg
    }

}