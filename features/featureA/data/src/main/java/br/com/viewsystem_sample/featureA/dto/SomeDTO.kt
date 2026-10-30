package br.com.viewsystem_sample.featureA.dto

import com.google.gson.annotations.SerializedName

data class SomeDTO(
    @SerializedName("serverId") val id: Long?,
    @SerializedName("textId") val text: String?
)