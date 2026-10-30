package br.com.viewsystem_sample.featureA.mapper

import br.com.viewsystem_sample.featureA.dto.SomeDTO
import br.com.viewsystem_sample.structural.local_storage.db.entity.SomeDbEntity

interface SomeMapper {

    fun toSomeDTOList(someDbEntityList: List<SomeDbEntity>): List<SomeDTO>

}