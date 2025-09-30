package br.com.viewsystem_sample.featureA.useCase

import br.com.viewsystem_sample.featureA.model.SomeModel
import kotlinx.coroutines.flow.Flow

interface GetSomethingUseCase {

    suspend operator fun invoke(): Flow<List<SomeModel>>

}