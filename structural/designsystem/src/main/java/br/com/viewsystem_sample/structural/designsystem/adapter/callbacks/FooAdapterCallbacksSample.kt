package br.com.viewsystem_sample.structural.designsystem.adapter.callbacks

import br.com.viewsystem_sample.structural.designsystem.adapter.callbacks.generic.SampleGenericAdapterCallback
import br.com.viewsystem_sample.structural.designsystem.adapter.model.Foo

interface FooAdapterCallbacksSample : SampleGenericAdapterCallback {

    fun onFooClicked(foo: Foo)

}