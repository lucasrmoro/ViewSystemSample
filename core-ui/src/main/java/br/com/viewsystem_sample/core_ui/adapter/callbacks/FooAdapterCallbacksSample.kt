package br.com.viewsystem_sample.core_ui.adapter.callbacks

import br.com.viewsystem_sample.core_ui.adapter.callbacks.generic.SampleGenericAdapterCallback
import br.com.viewsystem_sample.core_ui.adapter.model.Foo

interface FooAdapterCallbacksSample : SampleGenericAdapterCallback {

    fun onFooClicked(foo: Foo)

}