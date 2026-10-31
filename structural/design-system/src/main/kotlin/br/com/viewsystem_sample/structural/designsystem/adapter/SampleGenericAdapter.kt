package br.com.viewsystem_sample.structural.designsystem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.viewsystem_sample.structural.designsystem.adapter.callbacks.FooAdapterCallbacksSample
import br.com.viewsystem_sample.structural.designsystem.adapter.callbacks.generic.SampleGenericAdapterCallback
import br.com.viewsystem_sample.structural.designsystem.adapter.diffUtil.GenericItemDiffCallback
import br.com.viewsystem_sample.structural.designsystem.adapter.model.Foo
import br.com.viewsystem_sample.structural.designsystem.adapter.model.base.AdapterItem
import br.com.viewsystem_sample.structural.designsystem.adapter.model.base.AdapterViewType
import br.com.viewsystem_sample.structural.designsystem.adapter.viewHolder.FooViewHolder
import br.com.viewsystem_sample.structural.designsystem.adapter.viewHolder.LoadingViewHolder
import br.com.viewsystem_sample.structural.design_system.databinding.RvFooItemBinding
import br.com.viewsystem_sample.structural.design_system.databinding.RvLoadingItemBinding

class SampleGenericAdapter(
    private val adapterCallbacks: SampleGenericAdapterCallback? = null
) : ListAdapter<AdapterItem, RecyclerView.ViewHolder>(GenericItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (AdapterViewType.fromValue(viewType)) {
            AdapterViewType.LOADING ->
                LoadingViewHolder(RvLoadingItemBinding.inflate(inflater, parent, false))

            AdapterViewType.FOO ->
                FooViewHolder(
                    RvFooItemBinding.inflate(inflater, parent, false),
                    adapterCallbacks as? FooAdapterCallbacksSample
                )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = with(holder) {
        val item = getItem(position)

        when (this) {
            is FooViewHolder -> onBind(item as Foo)
        }
    }

    override fun getItemCount() = currentList.size

    override fun getItemViewType(position: Int): Int = currentList[position].itemViewType.ordinal

}