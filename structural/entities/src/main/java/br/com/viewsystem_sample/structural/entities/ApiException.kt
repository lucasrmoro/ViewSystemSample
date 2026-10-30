package br.com.viewsystem_sample.structural.entities

import com.google.gson.annotations.SerializedName

data class ApiException(
    @SerializedName("Status") val status: Int? = null,
    @SerializedName("Message") val message: String? = null,
    @SerializedName("ExceptionCode") val exceptionCode: Int? = null
)