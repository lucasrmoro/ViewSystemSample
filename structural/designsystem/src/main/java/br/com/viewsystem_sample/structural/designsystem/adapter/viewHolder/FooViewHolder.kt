package br.com.viewsystem_sample.structural.designsystem.adapter.viewHolder

import br.com.viewsystem_sample.structural.designsystem.adapter.base.BaseViewHolder
import br.com.viewsystem_sample.structural.designsystem.adapter.callbacks.FooAdapterCallbacksSample
import br.com.viewsystem_sample.structural.designsystem.adapter.model.Foo
import br.com.viewsystem_sample.structural.designsystem.databinding.RvFooItemBinding

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