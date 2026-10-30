package br.com.viewsystem_sample.structural.designsystem.adapter.model.base

interface DiffUtilEquality {

    fun areItemsTheSame(toCompare: Any): Boolean
    fun areContentsTheSame(toCompare: Any): Boolean = this == toCompare

}