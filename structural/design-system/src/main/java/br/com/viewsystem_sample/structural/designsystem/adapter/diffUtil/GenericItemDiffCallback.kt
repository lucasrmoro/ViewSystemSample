package br.com.viewsystem_sample.structural.designsystem.adapter.diffUtil

import androidx.recyclerview.widget.DiffUtil
import br.com.viewsystem_sample.structural.designsystem.adapter.model.base.AdapterItem

class GenericItemDiffCallback : DiffUtil.ItemCallback<AdapterItem>() {

    override fun areItemsTheSame(oldItem: AdapterItem, newItem: AdapterItem): Boolean =
        oldItem.areItemsTheSame(newItem)

    override fun areContentsTheSame(oldItem: AdapterItem, newItem: AdapterItem): Boolean =
        oldItem.areContentsTheSame(newItem)

}