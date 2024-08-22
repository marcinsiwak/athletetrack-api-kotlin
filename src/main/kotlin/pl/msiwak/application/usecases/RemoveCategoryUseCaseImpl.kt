package pl.msiwak.application.usecases

import pl.msiwak.domain.repositories.ExerciseRepository

class RemoveCategoryUseCaseImpl(
    private val exerciseRepository: ExerciseRepository
) : RemoveCategoryUseCase {

    override suspend fun invoke(categoryId: String) {
        exerciseRepository.removeCategory(categoryId)
    }
}
