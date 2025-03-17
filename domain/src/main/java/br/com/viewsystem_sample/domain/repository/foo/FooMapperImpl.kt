package br.com.viewsystem_sample.domain.repository.foo

import br.com.viewsystem_sample.core_ui.adapter.model.Foo
import br.com.viewsystem_sample.entities.db.FooDbEntity

internal class FooMapperImpl : FooMapper() {

    override fun toDomainModel(dto: FooDbEntity): Foo = with(dto) {
        Foo(id = id, msg = msg)
    }

    override fun toDTO(domainModel: Foo): FooDbEntity = with(domainModel) {
        FooDbEntity(id = id, msg = msg)
    }

}