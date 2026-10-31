package br.com.viewsystem_sample.structural.designsystem.adapter.model.base

interface AdapterItem : DiffUtilEquality {

    val itemViewType: AdapterViewType

}