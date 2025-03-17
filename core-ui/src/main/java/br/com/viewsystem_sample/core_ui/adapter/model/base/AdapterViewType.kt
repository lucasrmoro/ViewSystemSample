package br.com.viewsystem_sample.core_ui.adapter.model.base

enum class AdapterViewType {
    LOADING, FOO;

    companion object {
        fun fromValue(value: Int) = entries.toTypedArray()[value]
    }
}