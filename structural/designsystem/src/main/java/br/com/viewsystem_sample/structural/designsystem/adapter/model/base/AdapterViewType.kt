package br.com.viewsystem_sample.structural.designsystem.adapter.model.base

enum class AdapterViewType {
    LOADING, FOO;

    companion object {
        fun fromValue(value: Int) = entries.toTypedArray()[value]
    }
}