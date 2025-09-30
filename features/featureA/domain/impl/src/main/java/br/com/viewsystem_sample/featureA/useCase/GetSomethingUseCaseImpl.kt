package br.com.viewsystem_sample.featureA.useCase

import br.com.viewsystem_sample.featureA.model.SomeModel
import br.com.viewsystem_sample.featureA.repository.FeatureARepository
import br.com.viewsystem_sample.core.ext.ONE_HUNDRED
import br.com.viewsystem_sample.core.ext.ZERO
import kotlinx.coroutines.flow.Flow

class GetSomethingUseCaseImpl(
    private val featureARepository: FeatureARepository
) : GetSomethingUseCase {

    override suspend fun invoke(): Flow<List<SomeModel>> =
        featureARepository.getAll(page = Int.ZERO, itemsPerPage = Int.ONE_HUNDRED)

}