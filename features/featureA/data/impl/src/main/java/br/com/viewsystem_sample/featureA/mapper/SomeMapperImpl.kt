package br.com.viewsystem_sample.featureA.mapper

import br.com.viewsystem_sample.featureA.dto.SomeDTO
import br.com.viewsystem_sample.structural.local_storage.db.entity.SomeDbEntity

class SomeMapperImpl : SomeMapper {

    override fun toSomeDTOList(someDbEntityList: List<SomeDbEntity>) =
        someDbEntityList.map { SomeDTO(id = it.id, text = it.text) }

}