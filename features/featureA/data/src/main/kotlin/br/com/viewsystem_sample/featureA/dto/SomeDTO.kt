package br.com.viewsystem_sample.featureA.dto

import br.com.viewsystem_sample.structural.network.http.JsonName

data class SomeDTO(
    @JsonName("serverId") val id: Long?,
    @JsonName("textId") val text: String?
)