package br.com.viewsystem_sample.domain.repository.foo

import br.com.viewsystem_sample.core.base.mapper.BaseMapper
import br.com.viewsystem_sample.core_ui.adapter.model.Foo
import br.com.viewsystem_sample.entities.db.FooDbEntity

abstract class FooMapper : BaseMapper<FooDbEntity, Foo>()